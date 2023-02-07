package com.sh.obtg.share.model.dto;

import java.sql.Date;

public class ShareAttachment {
	private int attachNo;
	private int boardNo;
	private String originalFilename;
	private String renamedFilename;
	private Date regDate;
	

	//기본
	public ShareAttachment() {
		super();
	}


	//매 생
	public ShareAttachment(int attachNo, int boardNo, String originalFilename, String renamedFilename, Date regDate) {
		super();
		this.attachNo = attachNo;
		this.boardNo = boardNo;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.regDate = regDate;
	}


	// get set 
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
		return "ShareAttachment [attachNo=" + attachNo + ", boardNo=" + boardNo + ", originalFilename="
				+ originalFilename + ", renamedFilename=" + renamedFilename + ", regDate=" + regDate + "]";
	}


	
	
}

/**
 *  CREATE TABLE SHARE_attachment (
	attach_no	number		NOT NULL,
	board_no	number		NOT NULL,
	original_filename	varchar2(255)		NOT NULL,
	renamed_filename	varchar2(255)		NOT NULL,
	reg_date	Date		default sysdate,
    CONSTRAINT PK_SHARE_ATTACHMENT PRIMARY KEY (attach_no),
    CONSTRAINT FK_SHARE_board_SHARE_attachment_1 FOREIGN KEY (board_no) REFERENCES SHARE_board (SHARE_no) on delete cascade
);
**/

