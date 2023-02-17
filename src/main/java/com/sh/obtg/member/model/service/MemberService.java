package com.sh.obtg.member.model.service;

import static com.sh.obtg.common.JdbcTemplate.close;
import static com.sh.obtg.common.JdbcTemplate.commit;
import static com.sh.obtg.common.JdbcTemplate.getConnection;
import static com.sh.obtg.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.obtg.member.model.dao.MemberDao;
import com.sh.obtg.member.model.dto.Like;
import com.sh.obtg.member.model.dto.Member;
import com.sh.obtg.member.model.dto.Style;

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	/**
	 * 로그인 요청
	 * @param memberId
	 * @return
	 */
	public Member selectOneMember(String memberId) {
		Connection conn = getConnection();
		System.out.println(conn);
		Member member = memberDao.selectOneMember(conn, memberId);
		close(conn);
		return member;
	}
	
	public int updateMemberRole(String memberId, String memberRole) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMemberRole(conn, memberId, memberRole);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	public List<Member> searchMember(Map<String, String> param) {
		Connection conn = getConnection();
		List<Member> members = memberDao.searchMember(conn, param);
		close(conn);
		return members;
	}
	
	public List<Member> selectAllMember(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Member> members = memberDao.selectAllMember(conn, param);
		close(conn);
		return members;
	}
	
	public int selectTotalCount() {
		Connection conn = getConnection();
		int totalCount = memberDao.selectTotalCount(conn);
		close(conn);
		return totalCount;
	}
	public int deleteMemberAD(String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.deleteMemberAD(conn, memberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	public int updatePassword(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updatePassword(conn, member);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	
	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	public int insertMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	
	public int deleteMember(String memberId) {
		int result = 0;
		// 1. Connection객체 생성
		Connection conn = getConnection();
		try {
			// 2. dao 요청
			result = memberDao.deleteMember(conn, memberId);
			// 3. 트랜잭션 처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller 통보용
		} finally {
			// 4. Connection객체 반환
			close(conn);
		}
		return result;
	}
	
	// 내가 쓴 글이 몇개인가 조회(ootd + share)
	public int getMyPostTotalCount(String memberId) {
		Connection conn = getConnection();
		int ootdCnt = memberDao.selectMyOotdPostCnt(conn, memberId);
		int shareCnt = memberDao.selectMySharePostCnt(conn, memberId);
		close(conn);
		return ootdCnt + shareCnt;
	}
	
	
	// 내가 쓴 share글 수 조회
	public int getMySharePostCnt(String memberId) {
		Connection conn = getConnection();
		int shareCnt = memberDao.selectMySharePostCnt(conn, memberId);
		close(conn);
		return shareCnt;
	}
	
	// 내가 쓴 ootd글 수 조회
	public int getMyOotdPostCnt(String memberId) {
		Connection conn = getConnection();
		int ootdCnt = memberDao.selectMyOotdPostCnt(conn, memberId);
		close(conn);
		return ootdCnt;
	}
	
	// 나의 ootd 좋아요 조회
	public List<Like> selectOotdLike(String memberId) {
		Connection conn = getConnection();
		List<Like> ootdLikes = memberDao.selectOotdLike(conn, memberId);
		close(conn);
		return ootdLikes;
	}
	
	// 나의 share 좋아요 조회
	public List<Like> selectShareLike(String memberId) {
		Connection conn = getConnection();
		List<Like> ootdLikes = memberDao.selectShareLike(conn, memberId);
		close(conn);
		return ootdLikes;
	}
	
	// 이메일 조회
	public int selectEmail(String memberEmailId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.selectEmail(conn, memberEmailId);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	public int selectBlackList(String memberEmailId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.selectBlackList(conn, memberEmailId);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * 아이디 찾기
	 * @param email
	 * @return
	 */
	public String findMemebrId(String email) {
		Connection conn = getConnection();
		String memberId = memberDao.findMemebrId(conn, email);
		close(conn);
		return memberId;
	}

	/**
	 * 비밀번호 찾기
	 * @param param
	 * @return
	 */
	public int findMemebrPwd(Map<String, String> param) {
		Connection conn = getConnection();
		int member = memberDao.findMemebrPwd(conn, param);
		close(conn);
		return member;
	}

	/**
	 * 스타일 목록 조회
	 * @return
	 */
	public List<Style> selectStyleList() {
		Connection conn = getConnection();
		List<Style> styleList = memberDao.selectStyleList(conn);
		close(conn);
		return styleList;
	}

	/**
	 * 중복 검사
	 * @param type
	 * @param email 
	 * @return
	 */
	public int checkDuplicate(String type, String keyword) {
		Connection conn = getConnection();
		int count = memberDao.checkDuplicate(conn, type, keyword);
		close(conn);
		return count;
	}

	/**
	 * 마이페이지 ootd list 조회
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> selectMyOotd(String memberId) {
		Connection conn = getConnection();
		List<Map<String, Object>> ootdList = memberDao.selectMyOotd(conn, memberId);
		close(conn);
		return ootdList;
	}

	/**
	 * 마이페이지 share list 조회
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> selectMyShare(String memberId) {
		Connection conn = getConnection();
		List<Map<String, Object>> shareList = memberDao.selectMyShare(conn, memberId);
		close(conn);
		return shareList;
	}

	/**
	 * 회원 정보 수정
	 * @param param
	 * @return
	 */
	public int updateMember(Map<String, String> param) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMember(conn, param);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	/**
	 * 프로필 사진 변경
	 * @param param
	 * @return
	 */
	public int updateProfile(Map<String, String> param) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateProfile(conn, param);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

}