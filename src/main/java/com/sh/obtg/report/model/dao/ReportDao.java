package com.sh.obtg.report.model.dao;

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

import com.sh.obtg.notification.model.dto.Notification;
import com.sh.obtg.report.model.dto.Reason;
import com.sh.obtg.report.model.dto.Report;
import com.sh.obtg.report.model.dto.Status;
import com.sh.obtg.report.model.exception.ReportException;

public class ReportDao {

private Properties prop = new Properties();
	
	public ReportDao() {
		String path = ReportDao.class.getResource("/sql/report/report-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("report 쿼리 로드 완료! - " + prop);
	}

	/**
	 * 신고 접수
	 * @param conn
	 * @param report
	 * @return
	 */
	public int insertReport(Connection conn, Report report) {
		// insert into report(report_no, reported_userId, board_no, report_reason) values ((select max(no) + 1 from noti), ?, ?, ?)
		String sql = prop.getProperty("insertReport");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, report.getReportedUserId());
			pstmt.setString(2, report.getBoardNo());
			pstmt.setString(3, report.getReportReason().name());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ReportException("👻신고 접수 오류👻", e);
		}
		
		return result;
	}

	/**
	 * 신고 내역 조회
	 * @param conn
	 * @param param
	 * @return
	 */
	public List<Report> selectReportList(Connection conn, Map<String, Object> param) {
		String sql = prop.getProperty("selectReportList");
		List<Report> reports = new ArrayList<>();
		int page = (int)param.get("page");
		int limit = (int)param.get("limit");
		
		int start = (page - 1) * limit + 1;
		int end = page * limit;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Report report = new Report();
					report.setReportNo(rset.getInt("report_no"));
					report.setReportedUserId(rset.getString("reported_userId"));
					report.setBoardNo(rset.getString("board_no"));
					report.setRegDate(rset.getDate("reg_date"));
					report.setReportStatus(rset.getString("report_status") != null ?
											Status.valueOf(rset.getString("report_status")) :
												null);
					report.setReportReason(rset.getString("report_reason") != null ?
											Reason.valueOf(rset.getString("report_reason")) :
												null);
					reports.add(report);
					
				}
				
			}
			
		} catch (SQLException e) {
			throw new ReportException("👻신고 조회 오류👻", e);
		}
		
		return reports;
	}

	/**
	 * 신고건수 조회
	 * @param conn
	 * @return
	 */
	public int selectTotalCount(Connection conn) {
		String sql = prop.getProperty("selectTotalCount");
		int totalCount = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rset = pstmt.executeQuery()) {
			while(rset.next()) {
				totalCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new ReportException("👻신고 수 조회 오류👻", e);
		}
		
		return totalCount;
	}

//	// 알림 등록
//	public int insertNoti(Connection conn, Notification noti) {
//		// insert into noti(no, receiver, message) values (seq_noti_no.nextval, ?, ?)
//		String sql = prop.getProperty("insertNoti");
//		int result = 0;
//		
//		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, noti.getReceiver());
//			pstmt.setString(2, noti.getMessage());
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			throw new ReportException("👻신고처리결과 알림 등록 오류👻", e);
//		}
//		
//		return result;
//	}

	/**
	 * 신고 처리
	 * @param conn
	 * @param reportNo
	 * @return
	 */
	public int updateReport(Connection conn, int reportNo) {
		// update report set report_status = 'O' where report_no = ?
		String sql = prop.getProperty("updateReport");
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reportNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ReportException("👻신고 업데이트 오류👻", e);
		}
		
		return result;
	}
}
