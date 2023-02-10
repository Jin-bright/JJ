package com.sh.obtg.share.model.dao;

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

import com.sh.obtg.share.model.dto.NshareAttachment;
import com.sh.obtg.share.model.dto.NshareBoard;
import com.sh.obtg.share.model.dto.ShareAttachment;
import com.sh.obtg.share.model.dto.ShareBoard;
import com.sh.obtg.share.model.dto.ShareBoardAndAttachment;
import com.sh.obtg.share.model.dto.Style;
import com.sh.obtg.share.model.dto.Subcategory;
import com.sh.obtg.share.model.exception.ShareBoardException;

public class ShareboardDao {
	
	//최초 설정 
	Properties prop = new Properties();
	
	public ShareboardDao() {
		String path = ShareboardDao.class.getResource("/sql/share/shareBoard-query.properties").getPath(); // 이게 빌드패스 경로래 -- 절대경로 
		System.out.println("path : " + path);
		try {
			prop.load( new FileReader(path));
		} catch (IOException e) {
			System.out.println("path 오류 ");
			e.printStackTrace();
		}
		System.out.println("[ShareBoard-Query 준비완료] + prop ");
	}

// **트랜잭션1	
// insertShareBoard = insert into SHARE_board values(seq_SHARE_board_no.nextval,?,?,?,default,default,?,?,?,?,?)
	
	public int insertShareBoard(Connection conn, ShareBoard shareBoard) {
		String sql = prop.getProperty("insertShareBoard");
		int result =0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, shareBoard.getMemberId());
			pstmt.setString(2, shareBoard.getShareTitle());
			pstmt.setString(3, shareBoard.getShareContent());
			pstmt.setDate(4, shareBoard.getShareBuyDate());
			pstmt.setString(5, shareBoard.getShareProductStatus());
			pstmt.setString(6, shareBoard.getShareCategory());
			pstmt.setString(7, shareBoard.getShareState());
			pstmt.setString(8, shareBoard.getStyleNo().toString());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ShareBoardException("ootd 게시판 > 게시글 등록 오류", e);
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
				throw new ShareBoardException(" share 게시판 > 게시글번호 조회 오류!", e);
			}
			return boardNo;
		}
		
// 첨부파일넣기 - dml 
// insertAttach = insert into attachment(no, board_no, original_filename, renamed_filename) values (seq_board_no.nextval,?,?,?)
// insertAttachment = insert into SHARE_attachment(attach_no, board_no, original_filename, renamed_filename) values (seq_ootd_attachment_no.nextval,?,?,?)

		public int insertAttachment(Connection conn, ShareAttachment attach) {
			String sql = prop.getProperty("insertAttachment");
			int result = 0; 
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){ 
				pstmt.setInt(1, attach.getBoardNo());
				pstmt.setString(2, attach.getOriginalFilename());
				pstmt.setString(3, attach.getRenamedFilename());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new ShareBoardException("ootd게시판 > 첨부파일 등록 오류!", e);
			}
			
			return result;
		}

	
//모든 share첨부파일 가져오기 		
		public List<ShareAttachment> viewShareAttachment(Connection conn, Map<String, Object> param) {
			String sql = prop.getProperty("viewShareAttachment");
			List<ShareAttachment> shareAttachments = new ArrayList<>();
			
			int page = (int)param.get("page");
			int limit = (int)param.get("limit");
			int start = (page -1) * limit + 1;
			int end = page * limit;
			
			try( PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				
				try( ResultSet rset = pstmt.executeQuery()){
					
					while(rset.next()) {
						ShareAttachment shareAttachment = new ShareAttachment();
						
						shareAttachment.setAttachNo( rset.getInt("attach_no"));
						shareAttachment.setBoardNo( rset.getInt("board_no"));
						shareAttachment.setOriginalFilename(rset.getString("original_filename"));
						shareAttachment.setRenamedFilename(rset.getString("renamed_filename"));
						shareAttachment.setRegDate( rset.getDate("reg_date"));
						
						shareAttachments.add(shareAttachment);					
					}
				}
				
			} catch (SQLException e) {
				throw new ShareBoardException(" 전체 사진 목록 가져오기 실패!", e); 
			}
			
			return shareAttachments;
		}
		
		
// 전체페이지수조회 dql - select count(*) from share_attachment  
		public int getTotalCount(Connection conn) {
			String sql = prop.getProperty("getTotalCount");
			int totalCount = 0;
			
			try( PreparedStatement pstmt = conn.prepareStatement(sql);
					 ResultSet rset = pstmt.executeQuery(); ){
					 
				 if(rset.next()) {
					 totalCount = rset.getInt(1);
				 }
							
			}catch (SQLException e) {
				throw new ShareBoardException(" 전체 ootd 게시판 게시글 수 가져오기 실패!", e);
			}
			return totalCount;
		}

//모든 share 게시물 가져오기 	
		public List<ShareBoard>  viewShareBoard(Connection conn, Map<String, Object> param) {
			String sql = prop.getProperty("viewShareBoard");
			List<ShareBoard> shareboards = new ArrayList<>();
			
			int page = (int)param.get("page");
			int limit = (int)param.get("limit");
			int start = (page -1) * limit + 1;
			int end = page * limit;
			
			try( PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				
				try( ResultSet rset = pstmt.executeQuery()){
					
					while(rset.next()) {
						ShareBoard shareBoard = new ShareBoard();
						
						shareBoard.setShareNo(rset.getInt("share_no"));
						shareBoard.setMemberId(rset.getString("member_id"));
						shareBoard.setShareTitle(rset.getString("SAHRE_TITLE"));
						shareBoard.setShareContent(rset.getString("sahre_content"));
						shareBoard.setShareBuyDate(rset.getDate("SHARE_BUY_DATE"));
						shareBoard.setShareProductStatus(rset.getString("SHARE_PRODUCT_STATUS"));
						shareBoard.setShareCategory(rset.getString("SHARE_CATEGORY"));
						shareBoard.setShareState(rset.getString("SHARE_STATE") );
						shareBoard.setStyleNo( Style.valueOf( rset.getString("style")));
						
						shareboards.add(shareBoard);					
					}
				}
				
			} catch (SQLException e) {
				throw new ShareBoardException(" 전체 사진 게시물 목록 가져오기 실패! - only 목록만", e); 
			}
			
			return shareboards;
		}

//게시글 한개 조회  dql  - select * from share_board where share_no = ? 
		public ShareBoard selectOneBoard(Connection conn, int no) {
			String sql = prop.getProperty("selectOneBoard");
			ShareBoard shareBoard = null;
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, no);
				
				try(ResultSet rset = pstmt.executeQuery()){
					while(rset.next()) {
						shareBoard = new ShareBoard();
						
						shareBoard.setShareNo(no);
						shareBoard.setMemberId(rset.getString("member_id"));
						shareBoard.setShareTitle(rset.getString("SAHRE_TITLE"));
						shareBoard.setShareContent( rset.getString("SAHRE_CONTENT"));
						shareBoard.setShareReadCount(rset.getInt("SAHRE_READ_COUNT"));
						shareBoard.setShareRegDate(rset.getDate("SAHRE_REG_DATE"));
						shareBoard.setShareBuyDate(rset.getDate("SHARE_BUY_DATE"));
						shareBoard.setShareProductStatus(rset.getString("SHARE_PRODUCT_STATUS"));
						shareBoard.setShareCategory(rset.getString("SHARE_CATEGORY"));
						shareBoard.setShareState(rset.getString("SHARE_STATE"));
						shareBoard.setStyleNo( Style.valueOf( rset.getString("style")));
						
					}
				}
				
			} catch (Exception e) {
				throw new ShareBoardException(" share 게시글 한건 조회 오류!", e);
			}
			
			return shareBoard;
		}
//boardno로 첨부파일 테이블 조회 
		public List<ShareAttachment> selectAttachmentByBoardNo(Connection conn, int no) {
			String sql = prop.getProperty("selectAttachmentByBoardNo"); // select * from ootd_Attachment where board_no = ?
			List<ShareAttachment> shareAttachments = new ArrayList<>();
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, no);
				
				try(ResultSet rset = pstmt.executeQuery()){
					while(rset.next()) {
						ShareAttachment shareAttachment = new ShareAttachment();
						
						shareAttachment.setAttachNo( rset.getInt("attach_no"));
						shareAttachment.setBoardNo( rset.getInt("board_no"));
						shareAttachment.setOriginalFilename(rset.getString("original_filename"));
						shareAttachment.setRenamedFilename(rset.getString("renamed_filename"));
						shareAttachment.setRegDate( rset.getDate("reg_date"));
						
						shareAttachments.add(shareAttachment);			
					}
				}
				
			} catch (Exception e) {
				throw new ShareBoardException("share 게시글 한건-(첨부파일테이블) 조회 오류!", e);
			}
			
			return shareAttachments;
		}

//update readcount 
		public int updateReadCount(Connection conn, int no) {
			String sql = prop.getProperty("updateReadCount");
			int result = 0;
			
			try( PreparedStatement pstmt = conn.prepareStatement(sql) ){
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new ShareBoardException("조회수 증가 오류!", e);
			}
			return result;
		}

//게시글 업데이트 - update 
//updateBoard = update share_board set sahre_title=?, sahre_content=?, share_buy_date=?, share_product_status=?, share_category=?, share_state = ?, style = ? where share_no = ?
		public int updateBoard(Connection conn, ShareBoard shareBoard) {
			String sql = prop.getProperty("updateBoardp");
			int result = 0;
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, shareBoard.getShareTitle() );
				pstmt.setString(2, shareBoard.getShareContent());
				pstmt.setDate(3, shareBoard.getShareBuyDate());
				pstmt.setString(4, shareBoard.getShareProductStatus());
				pstmt.setString(5, shareBoard.getShareCategory());
				pstmt.setString(6, shareBoard.getShareState() );
				pstmt.setString(7, shareBoard.getStyleNo().toString() );
				pstmt.setInt(8, shareBoard.getShareNo());
				
				result = pstmt.executeUpdate();
			}catch (SQLException e) {
				throw new ShareBoardException("게시물(글) 수정 오류!", e);
			}
			return result;
			
		}

//updateAttachment = update share_attachment set original_filename = ? , renamed_filename = ? where board_no = ?
		public int updateAttachment(Connection conn, ShareAttachment attach) {
			String sql = prop.getProperty("updateAttachment");
			int result = 0;

			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, attach.getOriginalFilename());
				pstmt.setString(2,attach.getRenamedFilename());
				pstmt.setInt(3,attach.getBoardNo());
			
				result = pstmt.executeUpdate();
			}catch (SQLException e) {
				throw new ShareBoardException("게시물( 첨부파일 ) 수정 오류!", e);
			}
			return result;
		}

		public int deleteBoard(Connection conn, int no) {
			String sql = prop.getProperty("deleteBoard");
			int result = 0 ;
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){ 
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new ShareBoardException("게시물( only ) 삭제 오류!", e);
			}
			
			return result;
		}

		// 좋아요 조회
		public int selectShareLike(Connection conn, Map<String, Object> param) {
			// select count(*) from SHARE_Likes where board_no = ? and member_id = ?
			String sql = prop.getProperty("selectShareLike");
			int likeCnt = 0;
			int boardNo = (int)param.get("boardNo");
			String memberId = (String)param.get("memberId");
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, boardNo);
				pstmt.setString(2, memberId);
				
				try (ResultSet rset = pstmt.executeQuery()) {
					while(rset.next()) {
						likeCnt = rset.getInt(1);
					}
				}
				
			} catch (SQLException e) {
				throw new ShareBoardException("👻좋아요 조회 오류👻", e);
			}
			
			return likeCnt;
		}

		// 좋아요 입력(추가)
		public int insertShareLike(Connection conn, Map<String, Object> param) {
			// insert into SHARE_Likes values (seq_ootd_likes_no.nextval, ?, ?)
			String sql = prop.getProperty("insertShareLike");
			int result = 0;
			int boardNo = (int)param.get("boardNo");
			String memberId = (String)param.get("memberId");
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, boardNo);
				pstmt.setString(2, memberId);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new ShareBoardException("👻좋아요 입력 오류👻", e);
			}
			
			return result;
		}

		// 좋아요 삭제
		public int deleteShareLike(Connection conn, int no) {
			// delete SHARE_Likes where board_no = ?
			String sql = prop.getProperty("deleteShareLike");
			int result = 0;
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, no);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new ShareBoardException("👻좋아요 삭제 오류👻", e);
			}
			
			return result;
		}//

		
		
		
		
		//find - memberId / 
//select  share_no, member_id, sahre_title, sahre_content, sahre_reg_date, share_product_status, share_category, share_state, style, attach_no,  original_filename, renamed_filename
//from share_board join share_attachment
//on share_board.share_no = share_attachment.board_no
//where share_board.member_Id = '%cathj%';
		
		public List<ShareBoardAndAttachment> searchShareBykeyWord(Connection conn, Map<String, String> param) {
			String sql = prop.getProperty("searchShareBykeyWord");
			
			String searchType = param.get("searchType"); // member_id | member_name | gender
			String searchKeyword = param.get("searchKeyword"); // #
			
			List<ShareBoardAndAttachment> shareBoardAndAttachments = new ArrayList<>();
			sql = sql.replace("#", searchType);
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, "%"+searchKeyword+"%");
				
				try(ResultSet rset = pstmt.executeQuery() ){
					while(rset.next()) {
						
						ShareBoardAndAttachment shareBoardAndAttachment = new ShareBoardAndAttachment();
						
						shareBoardAndAttachment.setShareNo(rset.getInt("share_no"));
						shareBoardAndAttachment.setMemberId(rset.getString("member_id"));
						shareBoardAndAttachment.setShareTitle(rset.getString("sahre_title"));
						shareBoardAndAttachment.setShareContent(rset.getString("sahre_content"));
						shareBoardAndAttachment.setShareRegDate(rset.getDate("sahre_reg_date"));
						shareBoardAndAttachment.setShareProductStatus(rset.getString("share_product_status"));
						shareBoardAndAttachment.setShareCategory(rset.getString("share_category"));
						shareBoardAndAttachment.setShareState(rset.getString("share_state"));
						shareBoardAndAttachment.setStyleNo( Style.valueOf( rset.getString("style")));
						
						shareBoardAndAttachment.setAttachNo( rset.getInt("attach_no"));
						shareBoardAndAttachment.setOriginalFilename( rset.getString("original_filename"));
						shareBoardAndAttachment.setRenamedFilename( rset.getString("renamed_filename"));
						
						shareBoardAndAttachments.add(shareBoardAndAttachment);
						
					}
				}	
			}catch (SQLException e) {
				throw new ShareBoardException("id로 게시글찾기 실패^^", e);
			}
				
			return shareBoardAndAttachments;
		}

		// 거래 상태 변경
		public int updateShareState(Connection conn, int boardNo) {
			// update share_board set share_state = ? where share_no = ?
			String sql = prop.getProperty("updateShareState");
			int result = 0;
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, "거래완료");
				pstmt.setInt(2, boardNo);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new ShareBoardException("👻거래 상태 변경 오류👻", e);
			}
			
			return result;
		}	
		
		
	//////////
		public List<ShareBoardAndAttachment> searchShareBykeyWord(Connection conn, Map<String, String> param,
				Map<String, Integer> paramPage) {
			
			String sql = prop.getProperty("searchShareBykeyWordSecond");
			
			String searchType = param.get("searchType"); // member_id | member_name | gender
			String searchKeyword = param.get("searchKeyword"); // #
			
			List<ShareBoardAndAttachment> shareBoardAndAttachmentsSecond = new ArrayList<>();
			sql = sql.replace("#", searchType);
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, "%"+searchKeyword+"%");
				pstmt.setInt(2, paramPage.get("start"));
				pstmt.setInt(3, paramPage.get("end"));
				
				
				try(ResultSet rset = pstmt.executeQuery() ){
					while(rset.next()) {
						
						ShareBoardAndAttachment shareBoardAndAttachmentsSecondeach = new ShareBoardAndAttachment();
						
						shareBoardAndAttachmentsSecondeach.setShareNo(rset.getInt("share_no"));
						shareBoardAndAttachmentsSecondeach.setMemberId(rset.getString("member_id"));
						shareBoardAndAttachmentsSecondeach.setShareTitle(rset.getString("sahre_title"));
						shareBoardAndAttachmentsSecondeach.setShareContent(rset.getString("sahre_content"));
						shareBoardAndAttachmentsSecondeach.setShareRegDate(rset.getDate("sahre_reg_date"));
						shareBoardAndAttachmentsSecondeach.setShareProductStatus(rset.getString("share_product_status"));
						shareBoardAndAttachmentsSecondeach.setShareCategory(rset.getString("share_category"));
						shareBoardAndAttachmentsSecondeach.setShareState(rset.getString("share_state"));
						shareBoardAndAttachmentsSecondeach.setStyleNo( Style.valueOf( rset.getString("style")));
						
						shareBoardAndAttachmentsSecondeach.setAttachNo( rset.getInt("attach_no"));
						shareBoardAndAttachmentsSecondeach.setOriginalFilename( rset.getString("original_filename"));
						shareBoardAndAttachmentsSecondeach.setRenamedFilename( rset.getString("renamed_filename"));
						
						shareBoardAndAttachmentsSecond.add(shareBoardAndAttachmentsSecondeach);
						
					}
				}	
			}catch (SQLException e) {
				throw new ShareBoardException("비동기+page 게시글찾기 실패^^", e);
			}
				
			return shareBoardAndAttachmentsSecond;
		}

		
		//전체find 게시물수를 구한다 - select 
		public int getFindTotalCount(Connection conn, Map<String, String> param) {
			String sql = prop.getProperty("getFindTotalCount");
			int FindtotalCount = 0;
			
			String searchType = param.get("searchType"); // member_id | member_name | gender
			String searchKeyword = param.get("searchKeyword"); // #
			
			sql = sql.replace("#", searchType);
			
			
			try( PreparedStatement pstmt = conn.prepareStatement(sql)){
					pstmt.setString(1, "%"+searchKeyword+"%");
					
				
				 try(ResultSet rset = pstmt.executeQuery()){
				 
					 if(rset.next()) {
						 FindtotalCount = rset.getInt(1);
					 }
				 }
			} catch (SQLException e) {
				throw new ShareBoardException("전체 find item count 실패^^", e);
			}
			
			return FindtotalCount;
		}//

		
		///////////////////////////////////////////////////////////////////////////////////////////
		//★★★★새로게시긂번호구하기 
		public int selectLastNBoardNo(Connection conn) {
			String sql = prop.getProperty("selectLastNBoardNo");
			int boardNo = 0;
			
			try(
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rset = pstmt.executeQuery()){
				
				if(rset.next()) {
					boardNo = rset.getInt(1);
				}
				
			} catch (SQLException e) {
				throw new ShareBoardException(" 새로 진행하는 share 게시판 > 게시글번호 조회 오류!", e);
			}
			return boardNo;
		}

		//★★★★새로게시긂 넣기 - dml   
// insert into NSHARE_BOARD values(seq_SHARE_board_no.nextval,?,?,?,?,?,?,default,?,?,?,default,?)		
		public int insertNShareBoard(Connection conn, NshareBoard shareBoard) {
			String sql = prop.getProperty("insertNShareBoard");
			int result = 0;
			
			try( PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, shareBoard.getSubcategoryId().toString() );
				pstmt.setString(2, shareBoard.getMemberId());
				pstmt.setString(3, shareBoard.getStyleName().toString());
				pstmt.setString(4, shareBoard.getProductName());
				pstmt.setString(5, shareBoard.getProductContent());
				pstmt.setInt(6, shareBoard.getProductPrice());
				pstmt.setString(7, shareBoard.getProductColor());
				pstmt.setString(8, shareBoard.getProductGender());
				pstmt.setString(9, shareBoard.getProductStatus());
				pstmt.setString(10, shareBoard.getProductQuality());
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new ShareBoardException(" 새로 진행하는 share 게시판 > 게시글 등록 오류!", e);
			}
			return result;
		}

		//★★★★ 첨부파일 등록 
// insertNAttachment = insert into SHARE_attachment(attach_no, board_no, original_filename, renamed_filename) values (seq_ootd_attachment_no.nextval,?,?,?)
		public int insertNAttachment(Connection conn, NshareAttachment attach) {
			String sql = prop.getProperty("insertNAttachment");
				int result = 0; 
				
				try(PreparedStatement pstmt = conn.prepareStatement(sql)){ 
					pstmt.setInt(1, attach.getProductId() );
					pstmt.setString(2, attach.getOriginalFilename() );
					pstmt.setString(3, attach.getRenamedFilename() );
					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					throw new ShareBoardException(" 첨부파일 등록 오류!", e);
				}
				
				return result;
		}
		
		//★새로만드는 목록가져오는거 - 조인쿼리업이 게시판의일부 쿼리 + 사진테이블 가져올수있을까 
		
		public List<NshareBoard> viewNShareBoards(Connection conn, Map<String, Integer> param) {
			String sql = prop.getProperty("viewNShareBoards");
			List<NshareBoard> shareboards = new ArrayList<>();
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, param.get("start"));
				pstmt.setInt(2, param.get("end"));
				try( ResultSet rset = pstmt.executeQuery()){
					while( rset.next()) {
						NshareBoard shareboard = handleNshareBoard(rset);
						shareboards.add(shareboard);	
					}
				}
			} catch (SQLException e) {
				throw new ShareBoardException(" 게시글전체조회 오류!", e);
			}	
			return shareboards;
		}

		//refactoring
		private NshareBoard handleNshareBoard(ResultSet rset) throws SQLException {
			NshareBoard shareboard = new NshareBoard();
			shareboard.setProductId( rset.getInt("product_id"));
			shareboard.setSubcategoryId( Subcategory.valueOf( rset.getString("subcategory_id")));
			shareboard.setStyleName( Style.valueOf(  rset.getString("style_name")));
			shareboard.setProductName( rset.getString("product_name"));
			shareboard.setProductContent(rset.getString("product_content"));
			shareboard.setProductPrice( rset.getInt("product_price"));
			shareboard.setProductRegDate( rset.getDate("product_reg_date"));
			shareboard.setProductColor( rset.getString("product_color"));
			shareboard.setProductReadCount(rset.getInt("product_read_count"));
			shareboard.setProductGender(rset.getString("product_gender"));
			shareboard.setProductStatus( rset.getString("product_status"));
			shareboard.setProductQuality( rset.getString("product_quality"));
			return shareboard;
		}

		public List<NshareAttachment> viewNShareAttachment(Connection conn, Map<String, Integer> param) {
			String sql = prop.getProperty("viewNShareAttachment"); // select * from ootd_Attachment where board_no = ?
			List<NshareAttachment>shareAttachments = new ArrayList<>();
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, param.get("start"));
				pstmt.setInt(2, param.get("end"));
				
				try(ResultSet rset = pstmt.executeQuery()){
					while(rset.next()) {
						NshareAttachment shareAttachment = new NshareAttachment();
						
						shareAttachment.setProductAttachmentNo( rset.getInt("product_attachment_no"));
						shareAttachment.setProductId( rset.getInt("product_id"));
						shareAttachment.setOriginalFilename(rset.getString("product_attachment_original_filename"));
						shareAttachment.setRenamedFilename(rset.getString("product_attachment_renamed_filename"));
						shareAttachment.setRegDate( rset.getDate("product_attachment_reg_date"));
						
						shareAttachments.add(shareAttachment);			
					}
				}
				
			} catch (Exception e) {
				throw new ShareBoardException("share 게시글 한건-(첨부파일테이블) 조회 오류!", e);
			}
			
			return shareAttachments;
		}
/////////////////////
		public List<NshareAttachment> viewNShareAttachment(Connection conn) {
			String sql = prop.getProperty("viewNShareAttachmentre"); // select * from ootd_Attachment where board_no = ?
			List<NshareAttachment>shareAttachments = new ArrayList<>();
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				try(ResultSet rset = pstmt.executeQuery()){
					while(rset.next()) {
						NshareAttachment shareAttachment = new NshareAttachment();
						
						shareAttachment.setProductAttachmentNo( rset.getInt("product_attachment_no"));
						shareAttachment.setProductId( rset.getInt("product_id"));
						shareAttachment.setOriginalFilename(rset.getString("product_attachment_original_filename"));
						shareAttachment.setRenamedFilename(rset.getString("product_attachment_renamed_filename"));
						shareAttachment.setRegDate( rset.getDate("product_attachment_reg_date"));
						
						shareAttachments.add(shareAttachment);			
					}
				}
				
			} catch (Exception e) {
				throw new ShareBoardException("share 게시글 한건-(첨부파일테이블) 조회 오류!", e);
			}
			
			return shareAttachments;
		}

		public List<NshareBoard> viewNShareBoards(Connection conn) {
			String sql = prop.getProperty("viewNShareBoardsre");
			List<NshareBoard> shareboards = new ArrayList<>();
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				try( ResultSet rset = pstmt.executeQuery()){
					while( rset.next()) {
						NshareBoard shareboard = handleNshareBoard(rset);
						shareboards.add(shareboard);	
					}
				}
			} catch (SQLException e) {
				throw new ShareBoardException(" 게시글전체조회 오류!", e);
			}	
			return shareboards;
		}
}
