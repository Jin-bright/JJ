package com.sh.obtg.faq.model.dto;

import java.sql.Date;

public class faq {
	private int no;
	private String member_id;
	private String title;
	private String content;
	private String writer;
	private int readCount;
	private Date regDate;
	
	
	public faq() {
		super();
	}


	public faq(int no, String member_id, String title, String content, String writer, int readCount, Date regDate) {
		super();
		this.no = no;
		this.member_id = member_id;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.readCount = readCount;
		this.regDate = regDate;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		return "faq [no=" + no + ", member_id=" + member_id + ", title=" + title + ", content=" + content + ", writer="
				+ writer + ", readCount=" + readCount + ", regDate=" + regDate + "]";
	}


	
	
	
}
