package com.sh.obtg.column.model.dto;

import java.sql.Date;

public class Column {

	// Filed
	private int no;
	private String writer;
	private String title;
	private String subtitle;
	private String content;
	private String originalFilename;
	private String renamedFilename;
	private int readCount;
	private Date regDate;
	
	// constructor
	public Column() {}
	public Column(int no, String writer, String title, String subtitle, String content, String originalFilename,
			String renamedFilename, int readCount, Date regDate) {
		super();
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.subtitle = subtitle;
		this.content = content;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.readCount = readCount;
		this.regDate = regDate;
	}


	// getter setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
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
	
	// toString
	@Override
	public String toString() {
		return "Column [no=" + no + ", writer=" + writer + ", title=" + title + ", subtitle=" + subtitle + ", content="
				+ content + ", originalFilename=" + originalFilename + ", renamedFilename=" + renamedFilename
				+ ", readCount=" + readCount + ", regDate=" + regDate + "]";
	}
	
}