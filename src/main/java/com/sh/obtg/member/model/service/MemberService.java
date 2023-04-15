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

import oracle.net.aso.c;

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
	
	/**
	 * 사용자 권한 변경
	 * @param memberId
	 * @param memberRole
	 * @return
	 */
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
	
	/**
	 * 사용자 검색
	 * @param param
	 * @return
	 */
	public List<Member> searchMember(Map<String, String> param) {
		Connection conn = getConnection();
		List<Member> members = memberDao.searchMember(conn, param);
		close(conn);
		return members;
	}
	
	/**
	 * 모든 사용자 조회
	 * @param param
	 * @return
	 */
	public List<Member> selectAllMember(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Member> members = memberDao.selectAllMember(conn, param);
		close(conn);
		return members;
	}
	
	/**
	 * 모든 사용자수 조회
	 * @return
	 */
	public int selectTotalCount() {
		Connection conn = getConnection();
		int totalCount = memberDao.selectTotalCount(conn);
		close(conn);
		return totalCount;
	}
	
	/**
	 * 사용자 탈퇴처리
	 * @param memberId
	 * @return
	 */
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
	
	/**
	 * 사용자 탈퇴
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.deleteMember(conn, memberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
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
	 * 마이페이지 회원 정보 수정
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
	 * 마이페이지 프로필 사진 변경
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

	/**
	 * 마이페이지 share 목록 조회
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectMyShare(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Map<String, Object>> shareList = memberDao.selectMyShare(conn, param);
		close(conn);
		return shareList;
	}

	/**
	 * 마이페이지 share 목록 총 개수
	 * @param memberId
	 * @return
	 */
	public int myShareTotalCount(String memberId) {
		Connection conn = getConnection();
		int totalCount = memberDao.myShareTotalCount(conn, memberId);
		close(conn);
		return totalCount;
	}

	/**
	 * 마이페이지 share 목록 검색
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> searchShareList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Map<String, Object>> sharList = memberDao.searchSharedList(conn, param);
		close(conn);
		return sharList;
	}

	/**
	 * 마이페이지 share 목록 검색 총 개수 
	 * @param keyParam
	 * @return
	 */
	public int myShareTotalCount(Map<String, String> param) {
		Connection conn = getConnection();
		int totalCount = memberDao.myShareTotalCount(conn, param);
		close(conn);
		return totalCount;
	}

	/**
	 * 마이페이지 ootd 총 개수
	 * @return
	 */
	public int myOotdTotalCount(String memberId) {
		Connection conn = getConnection();
		int myOotdTotalCount = memberDao.myOotdTotalCount(conn, memberId);
		close(conn);
		return myOotdTotalCount;
	}

	/**
	 * 나의 ootd 좋아요 총 개수
	 * @param memberId
	 * @return
	 */
	public int myOotdLikeCount(String memberId) {
		Connection conn = getConnection();
		int myOotdLikeCount = memberDao.myOotdLikeCount(conn, memberId);
		close(conn);
		return myOotdLikeCount;
	}
	
	/**
	 * 마이페이지 ootd 목록 조회
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectMyOotdList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Map<String, Object>> ootdList = memberDao.selectMyOotdList(conn, param);
		close(conn);
		return ootdList;
	}

	/**
	 * 마이페이지 ootd 좋아요 조회
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectOotdLike(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Map<String, Object>> likeList = memberDao.selectOotdLike(conn, param);
		close(conn);
		return likeList;
	}

	/**
	 * 마이페이지 share 상태변경
	 * @param no
	 * @return
	 */
	public int updateShareStatus(int no) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateShareStatus(conn, no);
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
	 * 마이페이지 관심목록 조회
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> selectWishList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Map<String, Object>> wishList = memberDao.selectWishList(conn, param);
		close(conn);
		return wishList;
	}

	/**
	 * 마이페이지 관심목록 총개수
	 * @param memberId
	 * @return
	 */
	public int myWishListTotalCount(String memberId) {
		Connection conn = getConnection();
		int myWishListTotalCount = memberDao.myWishListTotalCount(conn, memberId);
		close(conn);
		return myWishListTotalCount;
	}

	/**
	 * 마이페이지(메인) 관심목록 조회
	 * @param memberId
	 * @return
	 */
	public List<Map<String, Object>> selectWish(String memberId) {
		Connection conn = getConnection();
		List<Map<String, Object>> wishList = memberDao.selectWish(conn, memberId);
		close(conn);
		return wishList;
	}

}