package com.sh.obtg.member.model.dao;

import static com.sh.obtg.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sh.obtg.admin.model.exception.AdminException;
import com.sh.obtg.member.model.dto.Gender;
import com.sh.obtg.member.model.dto.Like;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.dto.MemberRole;
import com.sh.obtg.member.model.dto.MyPost;
import com.sh.obtg.member.model.dto.MyPosts;
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
	

	public int updatePassword(Connection conn, Member member) {
		int result = 0;
		String sql = prop.getProperty("updatePassword");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("비밀번호 수정 오류!", e);
		}
		return result;
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
			
		} catch (SQLException e) {
			throw new MemberException("회원가입오류", e);
		}
		
		return result;
	}
	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		String sql = prop.getProperty("deleteMember");

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
	
	// 내가 쓴 ootd글 조회
	public List<MyPost> selectMyOotdPost(Connection conn, String memberId) {
		// select ootd_no, ootd_title, ootd_read_count, ootd_reg_date, renamed_filename from ootd_board b left join ootd_attachment a on b.ootd_no = a.board_no where ootd_writer = ?
		String sql = prop.getProperty("selectMyOotdPost");
		List<MyPost> ootdBoardList = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					MyPost mp = new MyPost();
					mp.setNo(rset.getInt("ootd_no"));
					mp.setTitle(rset.getString("ootd_title"));
					mp.setReadCount(rset.getInt("ootd_read_count"));
					mp.setRegDate(rset.getDate("ootd_reg_date"));
					mp.setRenamedFilename(rset.getString("renamed_filename"));
					ootdBoardList.add(mp);
				}
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻내가 쓴 ootd 게시물 조회 오류👻", e);
		}
		
		return ootdBoardList;
	}
	
	// 내가 쓴 share글 조회
	public List<MyPosts> selectMySharePost(Connection conn, String memberId) {
		String sql = prop.getProperty("selectMySharePost");
		List<MyPosts> shareBoardList = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);

			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					MyPosts mp = new MyPosts();
					mp.setNo(rset.getInt("SHARE_no"));
					mp.setTitle(rset.getString("SAHRE_TITLE"));
					mp.setRegDate(rset.getDate("SAHRE_REG_DATE"));
					mp.setReadCount(rset.getInt("SAHRE_READ_COUNT"));
					mp.setState(rset.getString("SHARE_STATE"));
					mp.setRenamedFilename(rset.getString("renamed_filename"));
					shareBoardList.add(mp);
				}
				
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻내가 쓴 share 게시물 조회 오류👻", e);
		}
		
		return shareBoardList;
	}
	
	// 나의 ootd 좋아요 조회
	public List<Like> selectOotdLike(Connection conn, String memberId) {
		String sql = prop.getProperty("selectOotdLike");
		List<Like> ootdLikes = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Like like = new Like();
					like.setNo(rset.getInt("ootd_no"));
					like.setTitle(rset.getString("ootd_title"));
					like.setRenamed_filename(rset.getString("renamed_filename"));
					ootdLikes.add(like);
				}
				
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻내가 좋아요한 ootd 조회 오류👻", e);
		}
		
		return ootdLikes;
	}
	
	// 나의 share 좋아요 조회
	public List<Like> selectShareLike(Connection conn, String memberId) {
		String sql = prop.getProperty("selectShareLike");
		List<Like> shareLikes = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Like like = new Like();
					like.setNo(rset.getInt("share_no"));
					like.setTitle(rset.getString("sahre_title"));
					like.setRenamed_filename(rset.getString("renamed_filename"));
					shareLikes.add(like);
				}
				
			}
			
		} catch (SQLException e) {
			throw new MemberException("👻내가 좋아요한 share 조회 오류👻", e);
		}
		
		return shareLikes;
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
			
		} catch (SQLException e) {
			throw new MemberException("👻아이디 찾기 오류👻", e);
		}
		
		return memberId;
	}
}
