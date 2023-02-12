package com.sh.obtg.member.model.dto;

public class Style {
	
	// field
	private String no;
	private String name;
	
	// constructor
	public Style() {}
	public Style(String no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	
	// getter setter
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "[Style]  no :" + no + ", name : " + name;
	}
}
