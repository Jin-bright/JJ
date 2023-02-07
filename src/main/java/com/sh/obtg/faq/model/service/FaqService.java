package com.sh.obtg.faq.model.service;

import static com.sh.obtg.common.JdbcTemplate.close;
import static com.sh.obtg.common.JdbcTemplate.commit;
import static com.sh.obtg.common.JdbcTemplate.getConnection;
import static com.sh.obtg.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.sh.obtg.faq.model.dao.FaqDao;
import com.sh.obtg.faq.model.dto.faq;
import com.sh.obtg.faq.model.dto.faqComment;

public class FaqService {
	
	private FaqDao faqDao = new FaqDao();

	public int insertFaq(faq f) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = faqDao.insertFaq(conn, f);
			
//			// 방금 등록된 faq.no컬럼값을 조회
//			int faqNo = faqDao.selectLastFaqNo(conn);
//			
//			f.setNo(faqNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteFaq(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = faqDao.deleteFaq(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public faq selectOneFaq(int faqNo, boolean hasRead) {
		Connection conn = getConnection();
		if(!hasRead) updateReadCount(faqNo, conn);
		faq f = faqDao.selectOneFaq(conn, faqNo);
		close(conn);
		return f;
	}
	
	public faq selectOneFaq(int faqNo) {
		return selectOneFaq(faqNo, true);
	}

	private void updateReadCount(int faqNo, Connection conn) {
		try {
			int result = faqDao.updateReadCount(conn, faqNo); 
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
	}

	public int updateFaq(faq faq) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = faqDao.updateFaq(conn, faq);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<faq> selectFaqList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<faq> faqList = faqDao.selectFaqList(conn, param);
		close(conn);
		return faqList;
	}

	public int selectTotalCount() {
		Connection conn = getConnection();
		int totalCount = faqDao.selectTotalCount(conn);
		close(conn);
		return totalCount;
	}
	
	public int insertFaqComment(faqComment faqComment) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = faqDao.insertFaqComment(conn, faqComment);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteFaqComment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = faqDao.deleteFaqComment(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public List<faqComment> selectFaqCommentList(int faqNo) {
		Connection conn = getConnection();
		List<faqComment> comments = faqDao.selectFaqCommentList(conn, faqNo);
		close(conn);
		return comments;
	}
}
