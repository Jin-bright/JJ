package com.sh.obtg.faq.model.dao;

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

import com.sh.obtg.faq.model.dto.faq;
import com.sh.obtg.faq.model.dto.faqComment;
import com.sh.obtg.faq.model.exception.FaqException;

public class FaqDao {
	
	private Properties prop = new Properties();
	
	public FaqDao() {
		String path = FaqDao.class.getResource("/sql/faq/faq-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("[Faq query load 완료!]" + prop);
	}

	public int insertFaq(Connection conn, faq f) {
		String sql = prop.getProperty("insertFaq");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, f.getWriter());
			pstmt.setString(2, f.getTitle());
			pstmt.setString(3, f.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new FaqException("게시물 저장 오류!", e);
		}
		return result;
	}

	public int deleteFaq(Connection conn, int no) {
		String sql = prop.getProperty("deleteFaq");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new FaqException("게시물 삭제 오류!", e);
		}
		return result;
	}

	public faq selectOneFaq(Connection conn, int faqNo) {
		String sql = prop.getProperty("selectOneFaq");
		faq faq = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, faqNo);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					faq = handleFaqResultSet(rset);
				}
			}
		} catch (Exception e) {
			throw new FaqException("게시물 한건 조회 오류!", e);
		}
		return faq;
	}

	private faq handleFaqResultSet(ResultSet rset) throws SQLException {
		faq faq = new faq();
		faq.setNo(rset.getInt("no"));
		faq.setWriter(rset.getString("writer"));
		faq.setTitle(rset.getString("title"));
		faq.setRegDate(rset.getDate("reg_date"));
		faq.setReadCount(rset.getInt("read_count"));
		faq.setContent(rset.getString("content"));
		return faq;
	}

	public int updateReadCount(Connection conn, int faqNo) {
		String sql = prop.getProperty("updateReadCount");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, faqNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new FaqException("게시물 조회수 처리 오류!", e);
		}
		return result;
	}

	public int updateFaq(Connection conn, faq faq) {
		String sql = prop.getProperty("updateFaq");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, faq.getTitle());
			pstmt.setString(2, faq.getContent());
			pstmt.setInt(3, faq.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new FaqException("게시물 업데이트 오류!", e);
		}
		return result;
	}

	public List<faq> selectFaqList(Connection conn, Map<String, Object> param) {
		String sql = prop.getProperty("selectFaqList");
		List<faq> faqList = new ArrayList<>();
		
		int page = (int) param.get("page");
		int limit = (int) param.get("limit");
		
		int start = (page - 1) * limit + 1;
		int end = page * limit;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
		
			try(ResultSet rset = pstmt.executeQuery()){
				
				while(rset.next()) {
					faq faq = handleFaqResultSet(rset);
					faqList.add(faq);
				}
			}
			
			
		} catch (Exception e) {
			throw new FaqException("게시글 목록 조회 오류!", e);
		}
		return faqList;
	}

	public int selectTotalCount(Connection conn) {
		String sql = prop.getProperty("selectTotalCount");
		int totalCount = 0;
		
		try (
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
	){
		if(rset.next()) {
			totalCount = rset.getInt(1);
		}
	} catch (SQLException e) {
		throw new FaqException("전체 게시글수 조회 오류!", e);
	}
		return totalCount;
	}
	
	
	
	public int insertFaqComment(Connection conn, faqComment faqComment) {
//		insert into faq_comment values(seq_faq_comment_no.nextval, ?, ?, ?, ?, default, ?)
		String sql = prop.getProperty("insertFaqComment"); // insert into board_comment values(seq_board_comment_no.nextval, ?, ?, ?, ?, ?, default)
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, faqComment.getCommentLevel());
			pstmt.setString(2, faqComment.getWriter());
			pstmt.setString(3, faqComment.getContent());
			pstmt.setInt(4, faqComment.getFaqNo());
			pstmt.setObject(5, faqComment.getCommentRef() == 0 ? null : faqComment.getCommentRef()); // 0
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			throw new FaqException("첨부파일 삭제 오류!", e);
		}
		return result;
	}

	public int deleteFaqComment(Connection conn, int no) {
		String sql = prop.getProperty("deleteFaqComment");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new FaqException("댓글 삭제 오류", e);
		}
		return result;
	}

	public List<faqComment> selectFaqCommentList(Connection conn, int faqNo) {
		String sql = prop.getProperty("selectFaqCommentList");
		List<faqComment> comments = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, faqNo);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					faqComment fc = new faqComment();
					fc.setNo(rset.getInt("no"));
					fc.setCommentLevel(rset.getInt("comment_level"));
					fc.setWriter(rset.getString("writer"));
					fc.setContent(rset.getString("content"));
					fc.setFaqNo(rset.getInt("faq_no"));
					fc.setCommentRef(rset.getInt("comment_ref"));
					fc.setRegDate(rset.getDate("reg_date"));
					comments.add(fc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

//	public int selectLastFaqNo(Connection conn) {
//		String sql = prop.getProperty("selectLastFaqNo");
//		int faqNo = 0;
//		try(
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			ResultSet rset = pstmt.executeQuery();
//		){
//			if(rset.next())
//				faqNo = rset.getInt(1);
//			
//		} catch (SQLException e) {
//			throw new FaqException("게시글번호 조회 오류!", e);
//		}
//		return faqNo;
//	}

	
}
