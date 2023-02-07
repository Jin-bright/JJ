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
import com.sh.obtg.member.model.dto.MyPost;
import com.sh.obtg.member.model.dto.MyPosts;

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
	public int updateMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}
	public int insertMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
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
	
	// 내가 쓴 ootd글 조회
	public List<MyPost> selectMyOotdPost(String memberId) {
		Connection conn = getConnection();
		List<MyPost> ootdBoardList = memberDao.selectMyOotdPost(conn, memberId);
		close(conn);
		return ootdBoardList;
	}
	
	// 내가 쓴 share글 조회
	public List<MyPosts> selectMySharePost(String memberId) {
		Connection conn = getConnection();
		List<MyPosts> shareBoardList = memberDao.selectMySharePost(conn, memberId);
		close(conn);
		return shareBoardList;
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
}