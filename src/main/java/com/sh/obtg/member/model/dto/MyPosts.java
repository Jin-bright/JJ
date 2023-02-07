package com.sh.obtg.member.model.dto;

import java.sql.Date;

public class MyPosts extends MyPost {
	
	private String state;
	
	public MyPosts() {
		super();
	}
	public MyPosts(int no, String title, int readCount, Date regDate, String renamedFilename) {
		super(no, title, readCount, regDate, renamedFilename);
	}
	public MyPosts(String state) {
		super();
		this.state = state;
	}
	
	// getter setter
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
