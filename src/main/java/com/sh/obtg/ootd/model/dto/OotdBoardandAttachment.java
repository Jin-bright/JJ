package com.sh.obtg.ootd.model.dto;

import java.sql.Date;

public class OotdBoardandAttachment {
	
	private int ootdNo;
	private String ootdWriter;
	private Style styleNo;
	private String OOTDTitle;
	private String OOTDContents;

	private Date OOTDRegDate;
	private String OOTDTop;
	private String OOTDBottom;
	private String OOTDShoes;
	private String OOTDEtc;
	
	private int attachNo;
	private String originalFilename;
	private String renamedFilename;
	
	
	
	//기본생성자 
	public OotdBoardandAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//매개변수생성자
	public OotdBoardandAttachment(int ootdNo, String ootdWriter, Style styleNo, String oOTDTitle, String oOTDContents,
			Date oOTDRegDate, String oOTDTop, String oOTDBottom, String oOTDShoes, String oOTDEtc, int attachNo,
			String originalFilename, String renamedFilename) {
		super();
		this.ootdNo = ootdNo;
		this.ootdWriter = ootdWriter;
		this.styleNo = styleNo;
		OOTDTitle = oOTDTitle;
		OOTDContents = oOTDContents;
		OOTDRegDate = oOTDRegDate;
		OOTDTop = oOTDTop;
		OOTDBottom = oOTDBottom;
		OOTDShoes = oOTDShoes;
		OOTDEtc = oOTDEtc;
		this.attachNo = attachNo;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
	}
	
	
	
	//get set 
	public int getOotdNo() {
		return ootdNo;
	}
	

	public void setOotdNo(int ootdNo) {
		this.ootdNo = ootdNo;
	}
	public String getOotdWriter() {
		return ootdWriter;
	}
	public void setOotdWriter(String ootdWriter) {
		this.ootdWriter = ootdWriter;
	}
	public Style getStyleNo() {
		return styleNo;
	}
	public void setStyleNo(Style styleNo) {
		this.styleNo = styleNo;
	}
	public String getOOTDTitle() {
		return OOTDTitle;
	}
	public void setOOTDTitle(String oOTDTitle) {
		OOTDTitle = oOTDTitle;
	}
	public String getOOTDContents() {
		return OOTDContents;
	}
	public void setOOTDContents(String oOTDContents) {
		OOTDContents = oOTDContents;
	}
	public Date getOOTDRegDate() {
		return OOTDRegDate;
	}
	public void setOOTDRegDate(Date oOTDRegDate) {
		OOTDRegDate = oOTDRegDate;
	}
	public String getOOTDTop() {
		return OOTDTop;
	}
	public void setOOTDTop(String oOTDTop) {
		OOTDTop = oOTDTop;
	}
	public String getOOTDBottom() {
		return OOTDBottom;
	}
	public void setOOTDBottom(String oOTDBottom) {
		OOTDBottom = oOTDBottom;
	}
	public String getOOTDShoes() {
		return OOTDShoes;
	}
	public void setOOTDShoes(String oOTDShoes) {
		OOTDShoes = oOTDShoes;
	}
	public String getOOTDEtc() {
		return OOTDEtc;
	}
	public void setOOTDEtc(String oOTDEtc) {
		OOTDEtc = oOTDEtc;
	}
	public int getAttachNo() {
		return attachNo;
	}
	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
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

	
	

}
