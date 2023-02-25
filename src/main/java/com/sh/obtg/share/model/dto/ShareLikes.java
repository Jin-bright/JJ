package com.sh.obtg.share.model.dto;

public class ShareLikes {
	
	private int likeNo;
	private int boardNo;
	private String memberId;
	
	//기본
	public ShareLikes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//매개
	public ShareLikes(int likeNo, int boardNo, String memberId) {
		super();
		this.likeNo = likeNo;
		this.boardNo = boardNo;
		this.memberId = memberId;
	}
	//getset 
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "ShareLike [likeNo=" + likeNo + ", boardNo=" + boardNo + ", memberId=" + memberId + "]";
	}
	
	

}
