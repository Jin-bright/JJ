package com.sh.obtg.chatting.model.dto;

public class Chatroom {

	private String chatroomId;
	private String boardId;
	private String memberId;
	
	//기본
	public Chatroom() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//매개변수
	public Chatroom(String chatroomId, String boardId, String memberId) {
		super();
		this.chatroomId = chatroomId;
		this.boardId = boardId;
		this.memberId = memberId;
	}
	
	//getset
	public String getChatroomId() {
		return chatroomId;
	}
	public void setChatroomId(String chatroomId) {
		this.chatroomId = chatroomId;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	//tostring
	@Override
	public String toString() {
		return "Chatroom [chatroomId=" + chatroomId + ", boardId=" + boardId + ", memberId=" + memberId + "]";
	}
	
	
}
