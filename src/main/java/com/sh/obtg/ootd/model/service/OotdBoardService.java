package com.sh.obtg.ootd.model.service;


import static com.sh.obtg.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.obtg.ootd.model.dao.OotdBoardDao;
import com.sh.obtg.ootd.model.dto.OotdAttachment;
import com.sh.obtg.ootd.model.dto.OotdBoard;
import com.sh.obtg.ootd.model.dto.OotdBoardComment;
import com.sh.obtg.ootd.model.dto.OotdBoardandAttachment;

 public class OotdBoardService {
	private OotdBoardDao ootdBoardDao = new OotdBoardDao();
	
	
	// 1. ootdBoard게시판에 글 등록 하는거 - insert - dml 
	public int insertOotdBoard(OotdBoard ootdBoard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			//게시글 등록은 변경없음 
			result = ootdBoardDao.insertBoard(conn, ootdBoard); //**트랜잭션1
			
			
			//방금 등록된 board.no컬럼값을 조회 - 시퀀스 객체의 현재값 
			int boardNo = ootdBoardDao.selectLastBoardNo(conn); // select seq_board_no.currval from dual //**트랜잭션2
			System.out.println("  boardNo : " +  boardNo );
			
			ootdBoard.setOotdNo(boardNo);//생성된 pk를 board객체에 다시 주입
			
			//첨부파일 등록은 반복문을 통해 여러번 처리되어야됨 
			List<OotdAttachment> ootdAttachments = ootdBoard.getOotdAttachments();
			
			if( !ootdAttachments.isEmpty() ) {
				for(OotdAttachment attach : ootdAttachments) {
					attach.setBoardNo(boardNo); //fk값 셋팅 필요 
					result = ootdBoardDao.insertAttachment(conn,attach);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

// dql  - page 에 나올 리스트 구하기    --- ( 5개씩은 나중에  )
	public List<OotdAttachment> selectOotdList(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<OotdAttachment> ootdAttachments =  ootdBoardDao.selectOotdList(conn, param);
		close(conn);
		return ootdAttachments;
	}

	
// 전체 ootd 게시물 수(첨부파일 테이블) 조회 dql - select count(*) from OOTD_attachment 
	public int getTotalCount() {
		Connection conn = getConnection();
		int totalCount = ootdBoardDao.getTotalCount(conn);
		close(conn);
		return totalCount;
	}

// 모든 ootd 첨부파일 가져오기 select * from 	ootd_attachment 
	public List<OotdAttachment> viewOotdAttachment(Map<String, Object> param) {
		Connection conn = getConnection();
		List<OotdAttachment> ootdAttachments = ootdBoardDao.viewOotdAttachment(conn, param);
		close(conn);
		
		return ootdAttachments;
	}

//게시물 하나하나씩 조회하기
	
	public OotdBoard selectOneBoard(int no, boolean hasRead) {
		Connection conn = getConnection();
		//조회수 증가시키기 
		System.out.println("hasread 여부 : " + hasRead );
		if(!hasRead) updateReadCount(no, conn);
		
			
	//	updateReadCount(no,conn);
		
		OotdBoard ootdboard  = ootdBoardDao.selectOneBoard(conn, no);
		List<OotdAttachment> ootdAttachments = ootdBoardDao.selectAttachmentByBoardNo(conn, no);
		ootdboard.setOotdAttachments( ootdAttachments);
		close(conn);
		
		return ootdboard;
	}
	
	private void updateReadCount(int no, Connection conn) {
		try {	
			int result = ootdBoardDao.updateReadCount(conn, no);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		
	}

	//댓글 조회하기 - select - dql 
	public List<OotdBoardComment> selectBoardCommentList(int boardNo) {
		Connection conn = getConnection();
		List<OotdBoardComment> comments = ootdBoardDao.selectBoardCommentList( conn, boardNo);
		close(conn);
		return comments;
	}
	// 댓글 등록 
//쿼리 : insert into OOTD_board_comment values(seq_OOTD_board_comment_no.nextval,?,?,?,?,?,default)
	public int insertBoardComment(OotdBoardComment boardComment) {
		Connection conn = getConnection();
		int result = 0;
		try{
			result = ootdBoardDao.insertBoardComment( conn, boardComment );
			commit(conn);
		}catch( Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	//댓글 삭제
	public int boardCommentDelete(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = ootdBoardDao.boardCommentDelete(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally{
			close(conn);
		}
		
		return result;
	}

	// 업데이트 게시판에서 
	public OotdBoard selectOneBoard(int no) {
		return selectOneBoard(no, true); //true로 해야지 조회수증가안됨 

	}

//board 게시물 업데이트 	
//쿼리 : 	 update OOTD_board_comment  set title = ?, content = ?  where no = ? 
	public int updateBoard(OotdBoard ootdBoard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. board 수정
			result = ootdBoardDao.updateBoard(conn, ootdBoard);

			// 2. attachment에 등록
			List<OotdAttachment> attachments = ootdBoard.getOotdAttachments();
			System.out.println("**attachments 정보 : "  + attachments );
			if(!attachments.isEmpty()) {
				for(OotdAttachment attach : attachments) {
//				result = ootdBoardDao.insertAttachment(conn, attach);
				result = ootdBoardDao.updateAttachment(conn, attach);		
				}
			}

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			
			close(conn);			
		}
		return result;
	}

//첨부파일 1개 선택 
	public OotdAttachment selectOneAttachment(int attachNo) {
		Connection conn = getConnection();
		OotdAttachment attach = ootdBoardDao.selectOneAttachment(conn, attachNo);
		close(conn);
		return attach;
	}

// 게시글 내 첨부파일 삭제 	
	public int deleteAttachment(int attachNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// dao요청
			result = ootdBoardDao.deleteAttachment(conn, attachNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

//board no로 attachment 첨부파일 게시글 한개 삭제 
	public List<OotdAttachment> selectAttachmentByBoardNo(int boardno) {
		Connection conn = getConnection();
		List<OotdAttachment> attachment = ootdBoardDao.selectAttachmentByBoardNo(conn, boardno);

		return attachment;
	}

//oard no로 attachment 첨부파일 게시글 한개 삭제 (2) -- 게시글 삭제 + fk삭제조건으로 att테이블 내용도 같이 날라감 
	public int deleteBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {	
			result = ootdBoardDao.deleteBoard(conn,no);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		
		return result;
	}

	// 좋아요 조회
	public int selectOotdLike(Map<String, Object> param) {
		Connection conn = getConnection();
		int count = ootdBoardDao.selectOotdLike(conn, param);
		close(conn);
		return count;
	}

	// 좋아요 추가
	public int insertOotdLike(Map<String, Object> param) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			result = ootdBoardDao.insertOotdLike(conn, param);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	// 좋아요 삭제
	public int deleteOotdLike(int no) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			result = ootdBoardDao.deleteOotdLike(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	
/**	// id로 게시글 리스트 찾기 (only글)
	public List<OotdBoard> SearchOotdBymemberId(Map<String, String> param) {
		Connection conn = getConnection();
		List<OotdBoard> findootdBoardsById = ootdBoardDao.SearchOotdBymemberId(conn, param);
		close(conn);
		return findootdBoardsById;
	}
	**/
	
	public List<OotdBoardandAttachment> SearchOotdBymemberId(Map<String, String> param){
		Connection conn = getConnection();
		List<OotdBoardandAttachment> ootdboardAndAttachments = ootdBoardDao.SearchOotdBymemberId(conn, param); 
		close(conn);
		return ootdboardAndAttachments;
	}


// 페이지  + 조인쿼리같이 처리해본다 
public List<OotdBoardandAttachment> SearchOotdBymemberStyle( Map<String, String> paramss) {
	Connection conn = getConnection();
	List<OotdBoardandAttachment> ootdboardAndAttachmentsbyStyle  =  ootdBoardDao.SearchOotdBymemberStyle( conn, paramss );
	
	close(conn);
	return ootdboardAndAttachmentsbyStyle;
}


//////////모든 ootd 첨부파일&게시글 같이  가져오기 select * from 	ootd_attachment 
	public List<OotdBoardandAttachment> viewOotdBoardandAttachment(Map<String, Object> param) {
		Connection conn = getConnection();
		List<OotdBoardandAttachment> ootdAttachments = ootdBoardDao.viewOotdBoardandAttachment(conn, param);
		close(conn);
		
		return ootdAttachments;
	}

	//총 좋아요수 
	public int selectOotdWholeLikes(int no) {
		Connection conn = getConnection();
		int likes = ootdBoardDao.selectOotdWholeLikes(conn, no);
		close(conn);
		return likes;
	}

	// 스타일별 옷찾기
	public List<OotdBoardandAttachment> findOotdBoardandAttachment(String searchKeyword, Map<String, Object> param ) {
		Connection conn = getConnection();
		List<OotdBoardandAttachment> findootdAll = ootdBoardDao.findOotdBoardandAttachment(conn, searchKeyword, param);
		close(conn);
		
		return findootdAll;
	}

	//최신순조회
	public List<OotdBoardandAttachment> viewOotdBoardandAttachmentByread(Map<String, Object> param) {
		Connection conn = getConnection();
		List<OotdBoardandAttachment> ootdAttachmentsByRead = ootdBoardDao.viewOotdBoardandAttachmentByread(conn, param);
		close(conn);
		
		return ootdAttachmentsByRead;
	}
	

}
