package com.sh.obtg.notification.model.dto;

import java.sql.Date;


public class Notification {

	// field
	private int no;
	private String receiver;
	private String message;
	private Date datetime;
	private Checked checked;
	// constructor
	
	public Notification() {}
	public Notification(int no, String receiver, String message, Date datetime, Checked checked) {
		super();
		this.no = no;
		this.receiver = receiver;
		this.message = message;
		this.datetime = datetime;
		this.checked = checked;
	}
	
	// getter setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Checked getChecked() {
		return checked;
	}
	public void setChecked(Checked checked) {
		this.checked = checked;
	}
	
	// toString
	@Override
	public String toString() {
		return "Notification [no=" + no + ", receiver=" + receiver + ", message=" + message + ", datetime=" + datetime
				+ ", checked=" + checked + "]";
	}
}
