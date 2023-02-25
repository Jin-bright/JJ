package com.sh.obtg.ootd.model.dao;

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

import com.sh.obtg.ootd.model.dto.OotdAttachment;
import com.sh.obtg.ootd.model.dto.OotdBoard;
import com.sh.obtg.ootd.model.dto.OotdBoardComment;
import com.sh.obtg.ootd.model.dto.OotdBoardandAttachment;
import com.sh.obtg.ootd.model.dto.Style;
import com.sh.obtg.ootd.model.exception.OotdBoardException;

public class OotdBoardDao {
	//최초 설정 
	Properties prop = new Properties();
	
	public OotdBoardDao() {
		System.out.println("왜?");
		String path = OotdBoardDao.class.getResource("/sql/ootd/ootdBoard-query.properties").getPath(); // 이게 빌드패스 경로래 -- 절대경로 
		System.out.println("path : " + path);
		try {
			prop.load( new FileReader(path));
		} catch (IOException e) {
			System.out.println("path 오류 ");
			e.printStackTrace();
		}
		System.out.println("[ootdboard-query 준비완료] + prop ");
	}

	// result = ootdBoardDao.insertBoard(conn, ootdBoard); //**트랜잭션1	
	public int insertBoard(Connection conn, OotdBoard ootdBoard) {
		String sql = prop.getProperty("insertBoard");
		int result =0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, ootdBoard.getOotdWriter());
			pstmt.setString(2, ootdBoard.getStyleNo().toString());
			pstmt.setString(3, ootdBoard.getOOTDTitle());
			pstmt.setString(4, ootdBoard.getOOTDContents());
			pstmt.setString(5, ootdBoard.getOOTDTop());
			pstmt.setString(6, ootdBoard.getOOTDBottom());
			pstmt.setString(7, ootdBoard.getOOTDShoes());
			pstmt.setString(8, ootdBoard.getOOTDEtc());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new OotdBoardException("ootd 게시판 > 게시글 등록 오류", e);
		}
		return result;
		
	}

	// ★시퀀스 번호 채번 - dql - select seq_board_no.currval from dual 
	public int selectLastBoardNo(Connection conn) {
		String sql = prop.getProperty("selectLastBoardNo");
		int boardNo = 0;
		
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery()){
			
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new OotdBoardException("ootd게시판 > 게시글번호 조회 오류!", e);
		}
		return boardNo;
	}

//첨부파일넣기 - dml 
//insertAttach = insert into attachment(no, board_no, original_filename, renamed_filename) values (seq_board_no.nextval,?,?,?)
	public int insertAttachment(Connection conn, OotdAttachment attach) {
		String sql = prop.getProperty("insertAttachment");
		int result = 0; 
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){ 
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new OotdBoardException("ootd게시판 > 첨부파일 등록 오류!", e);
		}
		
		return result;
	}

// dql 사진목록 출력하기  - cnt 
// 쿼리 : 	
	public List<OotdAttachment> selectOotdList(Connection conn,  Map<String, Integer> param) {
		String sql = prop.getProperty("selectOotdList");
		List<OotdAttachment> ootdAttachments = new ArrayList<>();
		try( PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			try( ResultSet rset = pstmt.executeQuery()){
				
				while(rset.next()) {
					OotdAttachment ootdAttachment = new OotdAttachment();
					
					ootdAttachment.setAttachNo( rset.getInt("attach_no"));
					ootdAttachment.setBoardNo( rset.getInt("board_no"));
					ootdAttachment.setOriginalFilename(rset.getString("original_filename"));
					ootdAttachment.setRenamedFilename(rset.getString("renamed_filename"));
					ootdAttachment.setRegDate( rset.getDate("reg_date"));
					
					ootdAttachments.add(ootdAttachment);					
				}
			}
			
		} catch (SQLException e) {
			throw new OotdBoardException(" 전체 사진 목록 가져오기 실패!", e); 
		}
		
		return ootdAttachments;
	}
	
	
	
	
	
// 전체페이지수조회 dql - select count(*) from OOTD_attachment  
	public int getTotalCount(Connection conn) {
		String sql = prop.getProperty("getTotalCount");
		int totalCount = 0;
		
		try( PreparedStatement pstmt = conn.prepareStatement(sql);
				 ResultSet rset = pstmt.executeQuery(); ){
				 
			 if(rset.next()) {
				 totalCount = rset.getInt(1);
			 }
						
		}catch (SQLException e) {
			throw new OotdBoardException(" 전체 ootd 게시판 게시글 수 가져오기 실패!", e); 
		}
		
		return totalCount;
	}

	
	
// 모든 ootd 첨부파일 가져오기 select * from 	ootd_attachment 
	public List<OotdAttachment> viewOotdAttachment(Connection conn, Map<String, Object> param) {
		String sql = prop.getProperty("selectOotdList");
		List<OotdAttachment> ootdAttachments = new ArrayList<>();
		
		int page = (int)param.get("page");
		int limit = (int)param.get("limit");
		int start = (page -1) * limit + 1;
		int end = page * limit;
		
		try( PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			try( ResultSet rset = pstmt.executeQuery()){
				
				while(rset.next()) {
					OotdAttachment ootdAttachment = new OotdAttachment();
					
					ootdAttachment.setAttachNo( rset.getInt("attach_no"));
					ootdAttachment.setBoardNo( rset.getInt("board_no"));
					ootdAttachment.setOriginalFilename(rset.getString("original_filename"));
					ootdAttachment.setRenamedFilename(rset.getString("renamed_filename"));
					ootdAttachment.setRegDate( rset.getDate("reg_date"));
					
					ootdAttachments.add(ootdAttachment);					
				}
			}
			
		} catch (SQLException e) {
			throw new OotdBoardException(" 전체 사진 목록 가져오기 실패!", e); 
		}
		
		return ootdAttachments;
	}

	//조회수 올리기 
	public int updateReadCount(Connection conn, int no) {
		String sql = prop.getProperty("updateReadCount");
		int result = 0;
		
		try( PreparedStatement pstmt = conn.prepareStatement(sql) ){
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new OotdBoardException("조회수 증가 오류!", e);
		}
		return result;
	}

// 게시글 한건조회 - board !
	public OotdBoard selectOneBoard(Connection conn, int no) {
		String sql = prop.getProperty("selectOneBoard");
		OotdBoard ootdboard = null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					ootdboard = new OotdBoard();
					
					ootdboard.setOotdNo( rset.getInt("OOTD_no"));
					ootdboard.setOotdWriter( rset.getString("OOTD_writer"));
					ootdboard.setStyleNo(Style.valueOf( rset.getString("style_no") ));
					ootdboard.setOOTDTitle(rset.getString("OOTD_title"));
					ootdboard.setOOTDContents( rset.getString("OOTD_contents"));
					ootdboard.setOOTDReadCount( rset.getInt("OOTD_read_count"));
					ootdboard.setOOTDRegDate( rset.getDate("OOTD_reg_date") );
					ootdboard.setOOTDTop( rset.getString("OOTD_top"));
					ootdboard.setOOTDBottom( rset.getString("OOTD_bottom"));
					ootdboard.setOOTDShoes( rset.getString("OOTD_shoes"));
					ootdboard.setOOTDEtc(rset.getString("OOTD_etc") );
				}
			}
			
		} catch (Exception e) {
			throw new OotdBoardException(" ootd 게시글 한건 조회 오류!", e);
		}
		
		return ootdboard;
	}

// 게시글 한건조회 - board 에서 첨부파일! ( boardNo랑 매칭되는거 )
	public List<OotdAttachment> selectAttachmentByBoardNo(Connection conn, int boardNo) {
		String sql = prop.getProperty("selectAttachmentByBoardNo"); // select * from ootd_Attachment where board_no = ?
		List<OotdAttachment> ootdAttachments = new ArrayList<>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, boardNo);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					OotdAttachment attach = handleAttachmentResultSet(rset);
					ootdAttachments.add(attach);
				}
			}
			
		} catch (Exception e) {
			throw new OotdBoardException("게시글 한건-(첨부파일테이블) 조회 오류!", e);
		}
		
		return ootdAttachments;
	}
	
	
	private OotdAttachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		OotdAttachment attach = new OotdAttachment();
		attach.setAttachNo( rset.getInt("attach_no"));
		attach.setBoardNo(rset.getInt("board_no"));
		attach.setOriginalFilename(rset.getString("original_filename"));
		attach.setRenamedFilename(rset.getString("renamed_filename"));
		attach.setRegDate(rset.getDate("reg_date"));
		return attach;
	}


//댓글조회하기 - 	
	public List<OotdBoardComment> selectBoardCommentList(Connection conn, int boardNo) {
		String sql = prop.getProperty("selectBoardCommentList");
		List<OotdBoardComment>  comments = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardNo);
			try(ResultSet rset = pstmt.executeQuery()){
				while( rset.next()) {
			
					OotdBoardComment bc = new OotdBoardComment();
					bc.setCmtNo(rset.getInt("cmt_no"));
					bc.setBoardNo( rset.getInt("board_no"));
					bc.setMemberId( rset.getString("member_id"));
					bc.setCmtLevel(rset.getInt("cmt_level"));
					bc.setCmtContent( rset.getString("cmt_content"));
					bc.setCmtRegDate(rset.getDate("cmt_reg_date"));
					bc.setCommentRef(rset.getInt("comment_ref"));
					comments.add(bc);
				}
			}//
		} catch (Exception e) {
			throw new OotdBoardException("댓글조회오류!", e);
		}		
		return comments;
	}
	
// 댓글 등록 
//주의 : comment_ref 컬럼값 셋팅시 , 0이면 setInt 안되서 setObject 사용해서 0이면 null을 대입할수있도록 한다 
//쿼리 : insert into board_comment values(seq_board_comment_no.nextval,?,?,?,?,default,?) where boardNo = ? 
	public int insertBoardComment(Connection conn, OotdBoardComment boardComment) {
		String sql = prop.getProperty("insertBoardComment");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, boardComment.getBoardNo());
			pstmt.setString(2,boardComment.getMemberId());
			pstmt.setInt(3, boardComment.getCmtLevel() );
			pstmt.setString(4, boardComment.getCmtContent() ); //  board_no
			pstmt.setObject(5, (boardComment.getCommentRef() == 0) ?  null : boardComment.getCommentRef()  ); //   comment_ref number

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new OotdBoardException("댓글 등록 오류!", e);
		}
		return result;	

	}

	//댓글 삭제
	public int boardCommentDelete(Connection conn, int no) {
		String sql = prop.getProperty("boardCommentDelete");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new OotdBoardException("댓글 삭제 오류!", e);
		}
		return result;
	}

//게시물업데이트 
// updateBoard = update OOTD_board set style_no=?, OOTD_title=?, OOTD_contents=?, OOTD_top=?, OOTD_bottom=?, OOTD_shoes=?, OOTD_etc=? where OOTD_no = ?
	public int updateBoard(Connection conn, OotdBoard ootdBoard) {
		String sql = prop.getProperty("updateBoard");
		int result = 0;

		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, ootdBoard.getStyleNo().toString());
			pstmt.setString(2, ootdBoard.getOOTDTitle());
			pstmt.setString(3, ootdBoard.getOOTDContents());
			pstmt.setString(4, ootdBoard.getOOTDTop());
			pstmt.setString(5, ootdBoard.getOOTDBottom());
			pstmt.setString(6, ootdBoard.getOOTDShoes());
			pstmt.setString(7, ootdBoard.getOOTDEtc());
			pstmt.setInt(8, ootdBoard.getOotdNo());
			
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new OotdBoardException("게시물 수정 오류!", e);
		}
		return result;
	}

	//첨부파일 한개 선택 
	public OotdAttachment selectOneAttachment(Connection conn, int attachNo) {
		String sql = prop.getProperty("selectOneAttachment");
		OotdAttachment attach = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, attachNo);
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next()) {
					attach = handleAttachmentResultSet(rset);
				}
			}
			
		} catch (SQLException e) {
			throw new OotdBoardException("첨부파일 한건 조회 오류", e);
		}
		
		return attach;
	}

	// 게시글  내 첨부파일 삭제 
	public int deleteAttachment(Connection conn, int attachNo) {
		String sql = prop.getProperty("deleteAttachment");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			// 미완성 쿼리 값대입
			pstmt.setInt(1, attachNo);
			// 실행
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			throw new OotdBoardException("첨부파일 삭제 오류!", e);
		}
		
		return result;
	}

	// 게시글 삭제 + 첨부파일 삭제 
	public int deleteBoard(Connection conn, int no) {
		String sql = prop.getProperty("deleteBoard");
		int result = 0 ;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){ 
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new OotdBoardException("게시글 삭제 오류(게시글or첨부파일 중 어딘가,,)!", e);
		}
		
		return result;
	}

	// 해당 ootd 게시글 좋아요 조회
	public int selectOotdLike(Connection conn, Map<String, Object> param) {
		// select count(*) from OOTD_Likes where board_no = ? and member_id = ?
		String sql = prop.getProperty("selectOotdLike");
		int count = 0;
		int boardNo = (int)param.get("boardNo");
		String memberId = (String)param.get("memberId");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, memberId);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					count = rset.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			throw new OotdBoardException("👻좋아요 조회 오류👻", e); 
		}
		
		return count;
	}

	// 좋아요 입력(추가)
	public int insertOotdLike(Connection conn, Map<String, Object> param) {
		// insert into OOTD_Likes values (seq_ootd_likes_no.nextval, ?, ?)
		String sql = prop.getProperty("insertOotdLike");
		int result = 0;
		int boardNo = (int)param.get("boardNo");
		String memberId = (String)param.get("memberId");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new OotdBoardException("👻좋아요 입력 오류👻", e); 
		}
		
		return result;
	}

	public int deleteOotdLike(Connection conn, int no) {
		// delete OOTD_Likes where board_no = ?
		int result = 0;
		String sql = prop.getProperty("deleteOotdLike");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new OotdBoardException("👻좋아요 삭제 오류👻", e); 
		}
		
		return result;
	}

// attachment바로 업데이트 시키기 도전 
//	updateAttachment = update ootd_attachment set original_filename = ? , renamed_filename = ? where board_no = ?
	public int updateAttachment(Connection conn, OotdAttachment attach) {
		String sql = prop.getProperty("updateAttachment");
		int result = 0;

		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, attach.getOriginalFilename());
			pstmt.setString(2,attach.getRenamedFilename());
			pstmt.setInt(3,attach.getBoardNo());
		
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new OotdBoardException("첨부파일 테이블 수정(update) 오류!", e);
		}
		return result;
	}


// 게시글 구해오기 : search type : 
// param.put("searchType", searchType);
// param.put("searchKeyword", searchKeyword);
// select * from ootd_attachment where board_no = ?
// 기존 쿼리  :searchMember = select * from member where # like ?
// select * from ootd_board where member_Id = ? tiger -> 검색해서나오는건 ? 그 ootd_board 정보  1개 
// select * from ootd_attachment where board_no = ? 
	
// select *from ootd_board where style_no = 로맨틱이면 S1, 이렇게 변경 작업 필요한데  ? 
// dql 	
/**
	public List<OotdBoard> SearchOotdBymemberId(Connection conn, Map<String, String> param) {
		String sql = prop.getProperty("SearchOotdBymemberId");
		
		String searchType = param.get("searchType"); // member_id | member_name | gender
		String searchKeyword = param.get("searchKeyword"); // #
		
		List<OotdBoard> findootdBoardsById =  new ArrayList<>();
		sql = sql.replace("#", searchType);
		//  select * from ootd_board where member_Id(=searchtype = #)  like  ? (keyword) 
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			try(ResultSet rset = pstmt.executeQuery() ){
				while(rset.next()) {
					OotdBoard ootdboard = new OotdBoard();
				
					ootdboard.setOotdNo( rset.getInt("OOTD_no"));
					ootdboard.setOotdWriter( rset.getString("OOTD_writer"));
					ootdboard.setStyleNo(Style.valueOf( rset.getString("style_no") ));
					ootdboard.setOOTDTitle(rset.getString("OOTD_title"));
					ootdboard.setOOTDContents( rset.getString("OOTD_contents"));
					ootdboard.setOOTDReadCount( rset.getInt("OOTD_read_count"));
					ootdboard.setOOTDRegDate( rset.getDate("OOTD_reg_date") );
					ootdboard.setOOTDTop( rset.getString("OOTD_top"));
					ootdboard.setOOTDBottom( rset.getString("OOTD_bottom"));
					ootdboard.setOOTDShoes( rset.getString("OOTD_shoes"));
					ootdboard.setOOTDEtc(rset.getString("OOTD_etc") );	
					
					findootdBoardsById.add(ootdboard);
				}
			}
			}catch (SQLException e) {
				throw new OotdBoardException("게시글 by memberid 찾기 오류!", e);
			}	
		return findootdBoardsById;
	
	}//
**/
	
	
//조인쿼리도전
	
	public List<OotdBoardandAttachment> SearchOotdBymemberId(Connection conn, Map<String, String> param) {
		String sql = prop.getProperty("SearchOotdBymemberId");
		
		String searchType = param.get("searchType"); // member_id | member_name | gender
		String searchKeyword = param.get("searchKeyword"); // #
		
		List<OotdBoardandAttachment> ootdboardAndAttachments = new ArrayList<>();
		sql = sql.replace("#", searchType);
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			try(ResultSet rset = pstmt.executeQuery() ){
				while(rset.next()) {
					OotdBoardandAttachment ootdboardAndAttachment = new OotdBoardandAttachment();
				
					ootdboardAndAttachment.setOotdNo( rset.getInt("OOTD_no"));
					ootdboardAndAttachment.setOotdWriter( rset.getString("OOTD_writer"));
					ootdboardAndAttachment.setStyleNo(Style.valueOf( rset.getString("style_no") ));
					ootdboardAndAttachment.setOOTDTitle(rset.getString("OOTD_title"));
					ootdboardAndAttachment.setOOTDContents( rset.getString("OOTD_contents"));
				
					ootdboardAndAttachment.setOOTDRegDate( rset.getDate("OOTD_reg_date") );
					ootdboardAndAttachment.setOOTDTop( rset.getString("OOTD_top"));
					ootdboardAndAttachment.setOOTDBottom( rset.getString("OOTD_bottom"));
					ootdboardAndAttachment.setOOTDShoes( rset.getString("OOTD_shoes"));
					ootdboardAndAttachment.setOOTDEtc(rset.getString("OOTD_etc") );	
					
					ootdboardAndAttachment.setAttachNo( rset.getInt("attach_no"));
					ootdboardAndAttachment.setOriginalFilename( rset.getString("original_filename"));
					ootdboardAndAttachment.setRenamedFilename( rset.getString("renamed_filename"));
					
					
					ootdboardAndAttachments.add(ootdboardAndAttachment);
				}
			}
		}catch (SQLException e) {
			throw new OotdBoardException("게시글 by memberid 찾기 오류!", e);
		}	
	return ootdboardAndAttachments;
	}

	
	
// 쿼리길다 
// select e.*from ( select ootd_no, ootd_writer, style_no, ootd_title, ootd_contents, ootd_reg_date, ootd_top, ootd_bottom, ootd_shoes, ootd_etc, attach_no, original_filename, renamed_filename,
// row_number() over(order by ootd_no  desc ) rnum from  ootd_board join ootd_attachment on ootd_board.ootd_no = ootd_attachment.board_no
// where # = ?) e   where rnum between ? and ?
// select * from  ootd_board  join ootd_attachment on ootd_board.ootd_no = ootd_attachment.board_no where # = ?
public List<OotdBoardandAttachment> SearchOotdBymemberStyle(Connection conn, Map<String, String> paramss) {

		String sql = prop.getProperty("SearchOotdBymemberStyle");
		
		String searchType = paramss.get("searchType"); // stylo_no 
		String searchKeyword = paramss.get("searchKeyword"); //  ---->  s1
		
		List<OotdBoardandAttachment>  ootdboardAndAttachmentsbyStyle   = new ArrayList<>();
		sql = sql.replace("#", searchType);
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			try(ResultSet rset = pstmt.executeQuery() ){
				while(rset.next()) {
					OotdBoardandAttachment ootdboardAndAttachmentsbyStyleone = new OotdBoardandAttachment();
				
					ootdboardAndAttachmentsbyStyleone.setOotdNo( rset.getInt("OOTD_no"));
					ootdboardAndAttachmentsbyStyleone.setOotdWriter( rset.getString("OOTD_writer"));
					ootdboardAndAttachmentsbyStyleone.setStyleNo(Style.valueOf( rset.getString("style_no") ));
					ootdboardAndAttachmentsbyStyleone.setOOTDTitle(rset.getString("OOTD_title"));
					ootdboardAndAttachmentsbyStyleone.setOOTDContents( rset.getString("OOTD_contents"));
				
					ootdboardAndAttachmentsbyStyleone.setOOTDRegDate( rset.getDate("OOTD_reg_date") );
					ootdboardAndAttachmentsbyStyleone.setOOTDTop( rset.getString("OOTD_top"));
					ootdboardAndAttachmentsbyStyleone.setOOTDBottom( rset.getString("OOTD_bottom"));
					ootdboardAndAttachmentsbyStyleone.setOOTDShoes( rset.getString("OOTD_shoes"));
					ootdboardAndAttachmentsbyStyleone.setOOTDEtc(rset.getString("OOTD_etc") );	
					
					ootdboardAndAttachmentsbyStyleone.setAttachNo( rset.getInt("attach_no"));
					ootdboardAndAttachmentsbyStyleone.setOriginalFilename( rset.getString("original_filename"));
					ootdboardAndAttachmentsbyStyleone.setRenamedFilename( rset.getString("renamed_filename"));
					
					
					ootdboardAndAttachmentsbyStyle.add(ootdboardAndAttachmentsbyStyleone);
				}
			}
		}catch (SQLException e) {
			throw new OotdBoardException("게시글 by memberid 찾기 오류!", e);
		}	
	return ootdboardAndAttachmentsbyStyle;
	
	}



public List<OotdBoardandAttachment> viewOotdBoardandAttachment(Connection conn, Map<String, Object> param) {
	String sql = prop.getProperty("viewOotdBoardandAttachment");
	List<OotdBoardandAttachment> ootdAttachments = new ArrayList<>();
	
	int page = (int)param.get("page");
	int limit = (int)param.get("limit");
	int start = (page -1) * limit + 1;
	int end = page * limit;
	
	try( PreparedStatement pstmt = conn.prepareStatement(sql)){
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		try( ResultSet rset = pstmt.executeQuery()){
			
			while(rset.next()) {
				OotdBoardandAttachment ootdboardAndAttachment = new OotdBoardandAttachment();
				
				ootdboardAndAttachment.setOotdNo( rset.getInt("OOTD_no"));
				ootdboardAndAttachment.setOotdWriter( rset.getString("OOTD_writer"));
				ootdboardAndAttachment.setStyleNo(Style.valueOf( rset.getString("style_no") ));
				ootdboardAndAttachment.setOOTDTitle(rset.getString("OOTD_title"));
				ootdboardAndAttachment.setOOTDContents( rset.getString("OOTD_contents"));
			
				ootdboardAndAttachment.setOOTDRegDate( rset.getDate("OOTD_reg_date") );
				ootdboardAndAttachment.setOOTDTop( rset.getString("OOTD_top"));
				ootdboardAndAttachment.setOOTDBottom( rset.getString("OOTD_bottom"));
				ootdboardAndAttachment.setOOTDShoes( rset.getString("OOTD_shoes"));
				ootdboardAndAttachment.setOOTDEtc(rset.getString("OOTD_etc") );	
				
				ootdboardAndAttachment.setAttachNo( rset.getInt("attach_no"));
				ootdboardAndAttachment.setOriginalFilename( rset.getString("original_filename"));
				ootdboardAndAttachment.setRenamedFilename( rset.getString("renamed_filename"));
				
				
				ootdAttachments.add(ootdboardAndAttachment);					
			}
		}
		
	} catch (SQLException e) {
		throw new OotdBoardException(" 전체 사진 목록 가져오기 실패!", e); 
	}
	
	return ootdAttachments;
}

	
}

	
	



