package com.sh.obtg.member.model.dto;

import java.sql.Date;

public class MyPost {
	
	private int no;
	private String title;
	private int readCount;
	private Date regDate;
	private String renamedFilename;
	
	public MyPost() {}
	public MyPost(int no, String title, int readCount, Date regDate, String renamedFilename) {
		super();
		this.no = no;
		this.title = title;
		this.readCount = readCount;
		this.regDate = regDate;
		this.renamedFilename = renamedFilename;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}	
}