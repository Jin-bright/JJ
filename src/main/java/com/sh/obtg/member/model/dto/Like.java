package com.sh.obtg.member.model.dto;

public class Like {
	
	private int no;
	private String title;
	private String renamed_filename;
	
	public Like() {}
	public Like(int no, String title, String renamed_filename) {
		super();
		this.no = no;
		this.title = title;
		this.renamed_filename = renamed_filename;
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
	public String getRenamed_filename() {
		return renamed_filename;
	}
	public void setRenamed_filename(String renamed_filename) {
		this.renamed_filename = renamed_filename;
	}
	
	
}
