package com.sh.obtg.ootd.model.dto;

import java.sql.Date;

public class OotdAttachment {
	private int attachNo;
	private int boardNo;
	private String originalFilename;
	private String renamedFilename;
	private Date regDate;
	
	//기본생성자
	public OotdAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//매개변수생성자
	public OotdAttachment(int attachNo, int boardNo, String originalFilename, String renamedFilename, Date regDate) {
		super();
		this.attachNo = attachNo;
		this.boardNo = boardNo;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.regDate = regDate;
	}
	public int getAttachNo() {
		return attachNo;
	}
	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "OotdAttachment [attachNo=" + attachNo + ", boardNo=" + boardNo + ", originalFilename="
				+ originalFilename + ", renamedFilename=" + renamedFilename + ", regDate=" + regDate + "]";
	}
	
	
	
}

/**
CREATE TABLE OOTD_attachment (
attach_no	number,
board_no	number		NOT NULL,
original_filename	varchar2(255)		NOT NULL, -- 프로필용
renamed_filename	varchar2(255)		NOT NULL, -- 프로필용
reg_date	Date	default sysdate,
CONSTRAINT PK_OOTD_ATTACHMENT PRIMARY KEY (attach_no),
CONSTRAINT FK_OOTD_board_OOTD_attachment_1 FOREIGN KEY (board_no) REFERENCES OOTD_board (OOTD_no) on delete cascade
);
**/
