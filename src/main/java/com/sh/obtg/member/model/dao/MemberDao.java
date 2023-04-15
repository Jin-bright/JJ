package com.sh.obtg.member.model.dao;

import static com.sh.obtg.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sh.obtg.admin.model.exception.AdminException;
import com.sh.obtg.column.model.dto.Column;
import com.sh.obtg.column.model.exception.ColumnException;
import com.sh.obtg.member.model.dto.Gender;
import com.sh.obtg.member.model.dto.Like;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.dto.MemberRole;
import com.sh.obtg.member.model.dto.Style;
import com.sh.obtg.member.model.exception.MemberException;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		String path = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("**member-query load 완료!**" + prop);
	}
	
	/**
	 * 로그인 요청
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public Member selectOneMember(Connection conn, String memberId) {
		String sql = prop.getProperty("selectOneMember");
		Member member = null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next()) {
					member = handleMemberResultSet(rset);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	private Member handleMemberResultSet(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setMemberId(rset.getString("member_id"));
		member.setStyle(rset.getString("style"));
		member.setName(rset.getString("name"));
		member.setPassword(rset.getString("password"));
		member.setEmail(rset.getString("email"));
		member.setPhone(rset.getString("phone"));
		member.setBirthday(rset.getDate("birthday"));
		member.setEnrollDate(rset.getTimestamp("enroll_date"));
		member.setMemberRole(MemberRole.valueOf(rset.getString("member_role")));
		member.setNickname(rset.getString("nickname"));	
		System.out.println("[" + rset.getString("gender") + "]");
		member.setGender(rset.getString("gender") != null ? 
				Gender.valueOf(rset.getString("gender")) : null);
		member.setIntroduce(rset.getString("introduce"));
		member.setOriginal(rset.getString("original"));
		member.setRenamed(rset.getString("renamed"));		
		return member;
	}

	public int updateMemberRole(Connection conn, String memberId, String memberRole) {
		String sql = prop.getProperty("updateMemberRole");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberRole);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("관리자 회원권한수정 오류",e);
		}
		
		return result;
	}
	public List<Member> searchMember(Connection conn, Map<String, String> param) {
		List<Member> members = new ArrayList<>();
		String searchType = param.get("searchType"); // member_id | member_name | gender
		String searchKeyword = param.get("searchKeyword");
		String sql = prop.getProperty("searchMember"); // select * from member where # like ?
		sql = sql.replace("#", searchType);
		System.out.println(sql);
		
		// 1. PreaparedStatement 객체 생성 & 미완성쿼리 값대입
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%" + searchKeyword + "%"); 
			// 2. 실행 & ResultSet 반환
			try(ResultSet rset = pstmt.executeQuery()){				
				// 3. ResultSet -> List<Member>
				while(rset.next())
					members.add(handleMemberResultSet(rset));
			}
		} catch (SQLException e) {
			throw new MemberException("관리자 회원검색 오류", e);
		}
		
		return members;
	}
	public List<Member> selectAllMember(Connection conn, Map<String, Object> param) {
		String sql = prop.getProperty("selectAllMember"); // select * from (select row_number() over(order by enroll_date desc) rnum, m.* from member m) where rnum between ? and ?
		List<Member> members = new ArrayList<>();
		int page = (int) param.get("page");
		int limit = (int) param.get("limit");
		int start = (page - 1) * limit + 1; 
		int end = page * limit;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			try(ResultSet rset = pstmt.executeQuery();){
				
				while(rset.next()) {
					Member member = handleMemberResultSet(rset);
					members.add(member);
				}
			}
			
			
		} catch (SQLException e) {
			throw new MemberException("관리자 회원목록조회 오류!", e);
		}
				
		return members;
	}
	public int selectTotalCount(Connection conn) {
		String sql = prop.getProperty("selectTotalCount"); // select count(*) from member
		int totalCount = 0;
		
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();	
		){
			while(rset.next())
				totalCount = rset.getInt(1); // 컬럼인덱스
	
		} catch (SQLException e) {
			throw new MemberException("전체 사용자수 조회 오류", e);
		}	
		
		return totalCount;
	}	
	public int deleteMemberAD(Connection conn, String memberId) {
		String sql = prop.getProperty("deleteMemberAD");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException("회원 추방에 실패했습니다.", e);
		}
		return result;
	}
	public int updateMember(Connection conn, Member member) {
		String sql = prop.getProperty("updateMember");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, member.getStyle());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setDate(5, member.getBirthday());
			pstmt.setString(6, member.getNickname());
			pstmt.setString(7, member.getGender().name());
			pstmt.setString(8, member.getIntroduce());
			pstmt.setString(9, member.getOriginal());
			pstmt.setString(10, member.getRenamed());
			pstmt.setString(11, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원정보 수정 오류", e);
		}
		return result;
	}
	/**
	 * 회원가입
	 * @param conn
	 * @param member
	 * @return
	 */
	public int insertMember(Connection conn, Member member) {
		String sql = prop.getProperty("insertMember");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getStyle());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPassword());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setDate(7, member.getBirthday());
			pstmt.setString(8, member.getNickname());
			pstmt.setString(9, member.getGender().name());
			pstmt.setString(10, member.getIntroduce());
			pstmt.setString(11, member.getOriginal());
			pstmt.setString(12, member.getRenamed());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new MemberException("👻회원가입 오류👻", e);
		}
		
		return result;
	}
	
	/**
	 * 회원 탈퇴
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public int deleteMember(Connection conn, String memberId) {
		String sql = prop.getProperty("deleteMember");
		int result = 0;

		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new MemberException("회원탈퇴 오류", e);
		}

		return result;
	
	}
	// 내가 쓴 ootd 게시글 수
	public int selectMyOotdPostCnt(Connection conn, String memberId) {
		// select count(*) from ootd_board where ootd_writer = ?
		String sql = prop.getProperty("selectMyOotdPostCnt");
		int count = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next())
					count = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻내가 쓴 ootd 게시글 수 조회 오류👻", e);
		}
		
		return count;
	}
	
	// 내가 쓴 share 게시글 수
	public int selectMySharePostCnt(Connection conn, String memberId) {
		// select count(*) from share_board where member_id = ?
		String sql = prop.getProperty("selectMySharePostCnt");
		int count = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next())
					count = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻내가 쓴 share 게시글 수 조회 오류👻", e);
		}
		
		return count;
	}
	
	public int selectEmail(Connection conn, String memberEmailId) {
		int result =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectEmail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmailId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result=rset.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AdminException("블랙리스트 조회 실패",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	public int selectBlackList(Connection conn, String memberEmailId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBlackList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmailId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result=rset.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AdminException("블랙리스트 조회 실패",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	// 리팩토링 ver. [made by 정은]
	
	/**
	 * 아이디 찾기
	 * @param conn
	 * @param email
	 * @return
	 */
	public String findMemebrId(Connection conn, String email) {
		// select member_id from member where email = ?
		String sql = prop.getProperty("findMemebrId");
		String memberId = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					memberId = rset.getString(1);
				}
			}
			
		} catch (Exception e) {
			throw new MemberException("👻아이디 찾기 오류👻", e);
		}
		
		return memberId;
	}

	/**
	 * 비밀번호 찾기
	 * @param conn
	 * @param param
	 * @return
	 */
	public int findMemebrPwd(Connection conn, Map<String, String> param) {
		// select count(*) from member where member_id = ? and email = ?
		String sql = prop.getProperty("findMemebrPwd");
		int member = 0;
		String memberId = param.get("memberId");
		String email = param.get("email");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setString(2, email);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					member = rset.getInt(1);
				}
			}
			
		} catch (Exception e) {
			throw new MemberException("👻비밀번호 찾기 오류👻", e);
		}
		
		return member;
	}

	/**
	 * 스타일 목록 조회
	 * @param conn
	 * @return
	 */
	public List<Style> selectStyleList(Connection conn) {
		// select * from fashionstyle
		String sql = prop.getProperty("selectStyleList");
		List<Style> styleList = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rset = pstmt.executeQuery()) {
			while(rset.next()) {
				Style style = new Style();
				style.setName(rset.getString("style"));
				styleList.add(style);
			}
			
		} catch (Exception e) {
			throw new MemberException("👻스타일 목록 조회 오류👻", e);
		}
		
		return styleList;
	}

	/**
	 * 중복 검사
	 * @param conn
	 * @param type
	 * @param keyword
	 * @return
	 */
	public int checkDuplicate(Connection conn, String type, String keyword) {
		// select count(*) from member where # = ?
		String sql = prop.getProperty("checkDuplicate");
		sql = sql.replace("#", type);
		int count = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, keyword);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					count = rset.getInt(1);
				}
			}
			
		} catch (Exception e) {
			throw new MemberException("👻중복검사 오류👻", e);
		}
		
		return count;
	}

	/**
	 * 마이페이지 ootd list 조회
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> selectMyOotd(Connection conn, String memberId) {
		String sql = prop.getProperty("selectMyOotd");
		List<Map<String, Object>> ootdList = new ArrayList<Map<String,Object>>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);

			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Map<String, Object> ootd = new HashMap<>();
					ootd.put("no", rset.getInt("OOTD_no"));
					ootd.put("title", rset.getString("OOTD_title"));
					ootd.put("readCount", rset.getInt("OOTD_read_count"));
					ootd.put("img", rset.getString("renamed_filename"));
					ootdList.add(ootd);
				}
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻마이페이지 ootd 목록 조회 오류👻", e);
		}
		
		return ootdList;
	}

	/**
	 * 마이페이지 share list 조회
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> selectMyShare(Connection conn, String memberId) {
		String sql = prop.getProperty("selectMyShare");
		List<Map<String, Object>> shareList = new ArrayList<Map<String,Object>>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);

			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Map<String, Object> share = new HashMap<>();
					share.put("no", rset.getInt("product_id"));
					share.put("name", rset.getString("product_name"));
					share.put("readCount", rset.getInt("product_read_count"));
					share.put("status", rset.getString("product_status"));
					share.put("img", rset.getString("product_attachment_renamed_filename"));
					share.put("regDate", rset.getDate("product_reg_date"));
					shareList.add(share);
				}
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻마이페이지 share 목록 조회 오류👻", e);
		}
		
		return shareList;
	}

	/**
	 * 회원 정보 수정
	 * @param conn
	 * @param param
	 * @return
	 */
	public int updateMember(Connection conn, Map<String, String> param) {
		// update member set # = ? where member_id = ?
		String sql = prop.getProperty("updateMember");
		String type = param.get("type");
		String keyword = param.get("keyword");
		String memberId = param.get("memberId");
		sql = sql.replace("#", type);
		int result = 0;
		
		Date birthday = null;
		if(type.equals("birthday")) {
			birthday = !"".equals(keyword) ? Date.valueOf(keyword) : null;
		}
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			if(type.equals("birthday")) {
				pstmt.setDate(1, birthday);
			}
			else {
				pstmt.setString(1, keyword);
			}
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();

		}
		catch (Exception e) {
			throw new MemberException("👻회원 정보 수정 오류👻", e);
		}
			
		return result;
	}
	
	/**
	 * 프로필 사진 변경
	 * @param conn
	 * @param param
	 * @return
	 */
	public int updateProfile(Connection conn, Map<String, String> param) {
		// update member set original = ?, renamed = ? where member_id = ?
		String sql = prop.getProperty("updateProfile");
		int result = 0;
		
		String original = param.get("original");
		String renamed = param.get("renamed");
		String memberId = param.get("memberId");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, original);
			pstmt.setString(2, renamed);
			pstmt.setString(3, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("👻프로필 사진 변경 오류👻", e);
		}
		
		return result;
	}
	
	/**
	 * 마이페이지 share 요소
	 * @param rset
	 * @return
	 * @throws SQLException
	 */
	private Map<String, Object> handleShareResultSet(ResultSet rset) throws SQLException {
		Map<String, Object> share = new HashMap<>();
		share.put("no", rset.getInt("product_id"));
		share.put("name", rset.getString("product_name"));
		share.put("readCount", rset.getInt("product_read_count"));
		share.put("status", rset.getString("product_status"));
		share.put("img", rset.getString("attach"));
		share.put("regDate", rset.getDate("product_reg_date"));
		return share;
	}
	
	/**
	 * 마이페이지 나눔 목록 조회
	 * @param conn
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectMyShare(Connection conn, Map<String, Object> param) {
		String sql = prop.getProperty("selectMyShare2");
		List<Map<String, Object>> shareList = new ArrayList<Map<String,Object>>();
		
		String memberId = (String)param.get("memberId");
		String sort = (String)param.get("sort");
		
		sql = sql.replace("@", sort);
		
		int page = (int) param.get("page"); 
		int limit = (int) param.get("limit");
		
		int start = (page - 1) * limit + 1;
		int end = page * limit;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Map<String, Object> share = new HashMap<>();
					share = handleShareResultSet(rset);
					shareList.add(share);
				}
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻마이페이지 share 목록 조회 오류👻", e);
		}
		
		return shareList;
	}
	
	/**
	 * 마이페이지 나눔 목록 총 개수 조회
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public int myShareTotalCount(Connection conn, String memberId) {
		// select count(*) from NSHARE_BOARD where member_id = ?
		String sql = prop.getProperty("myShareTotalCount");
		int totalCount = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					totalCount = rset.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻마이페이지 share 목록 총 개수 조회 오류👻", e);
		}
		
		return totalCount;
	}

	/**
	 * 마이페이지 나눔 목록 검색
	 * @param conn
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> searchSharedList(Connection conn, Map<String, Object> param) {
		String sql = prop.getProperty("searchSharedList");
		List<Map<String, Object>> shareList = new ArrayList<>();
		
		// 값 꺼내기
		String memberId = (String)param.get("memberId");
		String keyType = (String)param.get("keyType");
		String keyword = (String)param.get("keyword");
		String sort = (String)param.get("sort");
		int page = (int) param.get("page"); 
		int limit = (int) param.get("limit");
		
		sql = sql.replace("#", keyType);
		sql = sql.replace("@", sort);
		
		int start = (page - 1) * limit + 1;
		int end = page * limit;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setString(2, keyword);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Map<String, Object> share = new HashMap<>();
					share = handleShareResultSet(rset);
					shareList.add(share);
				}
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻마이페이지 share 목록 검색 오류👻", e);
		}
		
		return shareList;
	}

	/**
	 * 나눔 목록 검색 총 개수
	 * @param conn
	 * @param param
	 * @return
	 */
	public int myShareTotalCount(Connection conn, Map<String, String> param) {
		// select count(*) from NSHARE_BOARD where member_id = ? and # = ?
		String sql = prop.getProperty("myShareTotalCount2");
		int totalCount = 0;
		
		// 값 꺼내기
		String memberId = param.get("memberId");
		String keyType = param.get("keyType");
		String keyword = param.get("keyword");
		System.out.println("keyType : " + keyType);
		sql = sql.replace("#", keyType);
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			pstmt.setString(2, keyword);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					totalCount = rset.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻마이페이지 share 목록 검색 총 개수 조회 오류👻", e);
		}
		
		return totalCount;
	}

	/**
	 * 마이페이지 ootd 총 개수
	 * @param conn
	 * @return
	 */
	public int myOotdTotalCount(Connection conn, String memberId) {
		// select count(*) from ootd_board where member_id = ?
		String sql = prop.getProperty("myOotdTotalCount");
		int totalCount = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					totalCount = rset.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻마이페이지 ootd 총 개수 조회 오류👻", e);
		}
		
		return totalCount;
	}

	/**
	 * 나의 ootd 좋아요 총 개수
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public int myOotdLikeCount(Connection conn, String memberId) {
		// select count(*) from ootd_likes where member_id = ?
		String sql = prop.getProperty("myOotdLikeCount");
		int totalCount = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					totalCount = rset.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻마이페이지 ootd 좋아요 총 개수 조회 오류👻", e);
		}
		
		return totalCount;
	}

	/**
	 * 마이페이지 ootd 게시물 목록 조회
	 * @param conn
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectMyOotdList(Connection conn, Map<String, Object> param) {
		// select o.*, (select count(*) from ootd_likes where board_no = o.ootd_no) like_count, (select count(*) from ootd_likes where board_no = o.ootd_no and member_id = ?) my_like, (select renamed_filename from OOTD_attachment where board_no = o.ootd_no) img from ( select row_number() over(order by ootd_no desc) rnum, o.* from OOTD_board o where ootd_writer = ? ) o where rnum between ? and ?
		String sql = prop.getProperty("selectMyOotdList");
		List<Map<String, Object>> ootdList = new ArrayList<Map<String,Object>>();

		int start = (int)param.get("start");
		int end = (int)param.get("end");
		String memberId = (String)param.get("memberId");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Map<String, Object> ootd = new HashMap<>();
					ootd.put("no", rset.getInt("OOTD_no"));
					ootd.put("title", rset.getString("OOTD_title"));
					ootd.put("likeCount", rset.getInt("like_count"));
					ootd.put("myLike", rset.getInt("my_like"));
					ootd.put("img", rset.getString("img"));
					ootdList.add(ootd);
				}
			}
			
		} catch (SQLException e) {
			throw new ColumnException("👻 마이페이지 ootd 리스트 조회 오류 👻", e);
		}
		
		return ootdList;
	}

	/**
	 * 마이페이지 ootd 좋아요 목록 조회
	 * @param conn
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectOotdLike(Connection conn, Map<String, Object> param) {
		// select m.*, (select renamed_filename from OOTD_attachment where board_no = m.ootd_no) img from (select row_number() over(order by like_no desc) rnum, m.* from (select * from ootd_likes l left join ootd_board b on l.board_no = b.ootd_no) m where member_id = ?) m where rnum between ? and ?
		String sql = prop.getProperty("selectOotdLike");
		List<Map<String, Object>> likeList = new ArrayList<Map<String,Object>>();

		int start = (int)param.get("start");
		int end = (int)param.get("end");
		String memberId = (String)param.get("memberId");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Map<String, Object> ootd = new HashMap<>();
					ootd.put("no", rset.getInt("OOTD_no"));
					ootd.put("title", rset.getString("OOTD_title"));
					ootd.put("likeCount", rset.getInt("like_count"));
					ootd.put("img", rset.getString("img"));
					likeList.add(ootd);
				}
			}
			
		} catch (SQLException e) {
			throw new ColumnException("👻 마이페이지 ootd 좋아요 목록 조회 오류 👻", e);
		}
		
		return likeList;
	}

	/**
	 * 마이페이지 나눔상태 변경
	 * @param conn
	 * @param no
	 * @return
	 */
	public int updateShareStatus(Connection conn, int no) {
		// update NSHARE_BOARD set product_status = ? where product_id = ?
		String sql = prop.getProperty("updateShareStatus");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "거래완료");
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();

		}
		catch (Exception e) {
			throw new MemberException("👻 나눔 상태변경 오류 👻", e);
		}
			
		return result;
	}

	/**
	 * 마이페이지 관심목록 조회
	 * @param conn
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectWishList(Connection conn, Map<String, Object> param) {
		// select s.*, (select product_attachment_renamed_filename from nshare_attachment where product_id = s.product_id) img from (select row_number() over(order by like_no desc) rnum, s.* from (select * from share_likes l left join nshare_board b on l.board_no = b.product_id where l.member_id = ?) s) s where  rnum between ? and ?
		String sql = prop.getProperty("selectWishList");
		List<Map<String, Object>> wishList = new ArrayList<Map<String,Object>>();
		
		int page = (int) param.get("page"); 
		int limit = (int) param.get("limit");
		
		int start = (page - 1) * limit + 1;
		int end = page * limit;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, (String)param.get("memberId"));
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Map<String, Object> wish = new HashMap<>();
					wish.put("likeNo", rset.getInt("like_no"));
					wish.put("boardNo", rset.getInt("board_no"));
					wish.put("name", rset.getString("product_name"));
					wish.put("price", rset.getInt("product_price"));
					wish.put("img", rset.getString("img"));
					wishList.add(wish);
				}
			}
			
		} catch (Exception e) {
			throw new MemberException("👻 마이페이지 관심목록 조회 오류 👻", e);
		}
		
		return wishList;
	}

	/**
	 * 마이페지이 관심목록 총개수
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public int myWishListTotalCount(Connection conn, String memberId) {
		// select count(*) from share_likes where member_id = ?
		String sql = prop.getProperty("myWishListTotalCount");
		int totalCount = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					totalCount = rset.getInt(1);
				}
			}
			
		} catch (Exception e) {
			throw new MemberException("👻마이페이지 관심목록 총개수 조회 오류👻", e);
		}
		
		return totalCount;
	}

	/**
	 * 마이페이지(메인) 관심목록 조회
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> selectWish(Connection conn, String memberId) {
		// select s.*, (select product_attachment_renamed_filename from nshare_attachment where product_id = s.product_id) img from (select * from share_likes l left join nshare_board b on l.board_no = b.product_id where l.member_id = ?) s
		String sql = prop.getProperty("selectWish");
		List<Map<String, Object>> wishList = new ArrayList<Map<String,Object>>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Map<String, Object> wish = new HashMap<>();
					wish.put("likeNo", rset.getInt("like_no"));
					wish.put("boardNo", rset.getInt("board_no"));
					wish.put("name", rset.getString("product_name"));
					wish.put("price", rset.getInt("product_price"));
					wish.put("img", rset.getString("img"));
					wishList.add(wish);
				}
			}
			
		} catch (Exception e) {
			throw new MemberException("👻 마이페이지(메인) 관심목록 조회 오류 👻", e);
		}
		
		return wishList;
	}
}
