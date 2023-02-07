package com.sh.obtg.ootd.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class OotdBoard extends OotdBoardEntity {

	private int attachCnt; //실제테이블엔 없지만 필요해 -- 첨부파일 수 카운트 
	private List<OotdAttachment> ootdAttachments = new ArrayList<>();
	
	
	
	public OotdBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	//기본생성자
	public OotdBoard(int attachCnt) {
		super();
		this.attachCnt = attachCnt;
	}

	//부모 + attachcnt 넣어서생성자
	public OotdBoard(int ootdNo, String ootdWriter, Style styleNo, String oOTDTitle, String oOTDContents,
			int oOTDReadCount, Date oOTDRegDate, String oOTDTop, String oOTDBottom, String oOTDShoes, String oOTDEtc,
			int attachCnt) {
		super(ootdNo, ootdWriter, styleNo, oOTDTitle, oOTDContents, oOTDReadCount, oOTDRegDate, oOTDTop, oOTDBottom,
				oOTDShoes, oOTDEtc);
		this.attachCnt = attachCnt;
	}
	
	// 부모 + list 넣어서 생성자 
	public OotdBoard(int ootdNo, String ootdWriter, Style styleNo, String oOTDTitle, String oOTDContents,
			int oOTDReadCount, Date oOTDRegDate, String oOTDTop, String oOTDBottom, String oOTDShoes, String oOTDEtc,
			List<OotdAttachment> ootdAttachments) {
		super(ootdNo, ootdWriter, styleNo, oOTDTitle, oOTDContents, oOTDReadCount, oOTDRegDate, oOTDTop, oOTDBottom,
				oOTDShoes, oOTDEtc);
		this.ootdAttachments = ootdAttachments;
	}

	public int getAttachCnt() {
		return attachCnt;
	}

	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}

	public List<OotdAttachment> getOotdAttachments() {
		return ootdAttachments;
	}

	public void setOotdAttachments(List<OotdAttachment> ootdAttachments) {
		this.ootdAttachments = ootdAttachments;
	}

	
	
	@Override
	public String toString() {
		return "OotdBoard [attachCnt=" + attachCnt + ", ootdAttachments=" + ootdAttachments + ", getOotdNo()="
				+ getOotdNo() + ", getOotdWriter()=" + getOotdWriter() + ", getStyleNo()=" + getStyleNo()
				+ ", getOOTDTitle()=" + getOOTDTitle() + ", getOOTDContents()=" + getOOTDContents()
				+ ", getOOTDReadCount()=" + getOOTDReadCount() + ", getOOTDRegDate()=" + getOOTDRegDate()
				+ ", getOOTDTop()=" + getOOTDTop() + ", getOOTDBottom()=" + getOOTDBottom() + ", getOOTDShoes()="
				+ getOOTDShoes() + ", getOOTDEtc()=" + getOOTDEtc() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	 
	// 이거뭐지 ? ★★★
	public void addAttachment(OotdAttachment attach) {
		this.ootdAttachments.add(attach);
	}


	
	
	

	
	
}
