package com.sh.obtg.faq.model.dto;

import java.sql.Date;

public class faqComment {
	private int no;
	private int commentLevel; // 댓글(1), 대댓글(2)
	private String writer;
	private String content;
	private int faqNo;
	private int commentRef; // 대댓글인 경우만 참조댓글번호
	private Date regDate;
	
	
	
	public faqComment() {
		super();
	}
	
	public faqComment(int no, int commentLevel, String writer, String content, int faqNo, int commentRef,
			Date regDate) {
		super();
		this.no = no;
		this.commentLevel = commentLevel;
		this.writer = writer;
		this.content = content;
		this.faqNo = faqNo;
		this.commentRef = commentRef;
		this.regDate = regDate;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}


	public int getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getFaqNo() {
		return faqNo;
	}


	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}


	public int getCommentRef() {
		return commentRef;
	}


	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}


	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "faqComment [no=" + no + ", commentLevel=" + commentLevel + ", writer=" + writer + ", content=" + content
				+ ", faqNo=" + faqNo + ", commentRef=" + commentRef + ", regDate=" + regDate + "]";
	}
}
