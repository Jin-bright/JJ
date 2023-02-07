package com.sh.obtg.chatting.model.dto;

import java.sql.Date;

public class ChatLog {
	private int no;
	private String ChatroomId; 
	private String message;
	private Date datetime;
	
	//기본
	public ChatLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	//매개변수
	public ChatLog(int no, String chatroomId, String message, Date datetime) {
		super();
		this.no = no;
		ChatroomId = chatroomId;
		this.message = message;
		this.datetime = datetime;
	}
	
	//getset 
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getChatroomId() {
		return ChatroomId;
	}
	public void setChatroomId(String chatroomId) {
		ChatroomId = chatroomId;
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

	
	//
	@Override
	public String toString() {
		return "ChatLog [no=" + no + ", ChatroomId=" + ChatroomId + ", message=" + message + ", datetime=" + datetime
				+ "]";
	}
	
	
	
}
