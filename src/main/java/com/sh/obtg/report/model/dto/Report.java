package com.sh.obtg.report.model.dto;

import java.sql.Date;

public class Report {
	
	// Filed
	private int reportNo;
	private String reportedUserId;
	private String boardNo;
	private Date regDate;
	private Status reportStatus; 
	private Reason reportReason;
	
	// constructor
	public Report() {}
	public Report(int reportNo, String reportedUserId, String boardNo, Date regDate, Status reportStatus,
			Reason reportReason) {
		super();
		this.reportNo = reportNo;
		this.reportedUserId = reportedUserId;
		this.boardNo = boardNo;
		this.regDate = regDate;
		this.reportStatus = reportStatus;
		this.reportReason = reportReason;
	}
	
	// getter setter
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getReportedUserId() {
		return reportedUserId;
	}
	public void setReportedUserId(String reportedUserId) {
		this.reportedUserId = reportedUserId;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String no) {
		this.boardNo = no;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Status getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(Status reportStatus) {
		this.reportStatus = reportStatus;
	}
	public Reason getReportReason() {
		return reportReason;
	}
	public void setReportReason(Reason reportReason) {
		this.reportReason = reportReason;
	}
	
	// toString
	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reportedUserId=" + reportedUserId + ", boardNo=" + boardNo
				+ ", regDate=" + regDate + ", reportStatus=" + reportStatus + ", reportReason=" + reportReason + "]";
	}

}
