package com.sh.obtg.share.model.dto;

import java.sql.Date;

public class ShareBoardAndAttachment {
	private int ShareNo;
	private String memberId;
	private String ShareTitle;
	private String ShareContent;
	private Date ShareRegDate;
	private String ShareProductStatus;
	private String ShareCategory;
	private String ShareState; //거래전 - 거래중 - 거래완료
	private Style StyleNo; // 
	private int attachNo;
	private String originalFilename;
	private String renamedFilename;
	
	//기본
	public ShareBoardAndAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//매개변수
	public ShareBoardAndAttachment(int shareNo, String memberId, String shareTitle, String shareContent,
			Date shareRegDate, String shareProductStatus, String shareCategory, String shareState, Style styleNo,
			int attachNo, String originalFilename, String renamedFilename) {
		super();
		ShareNo = shareNo;
		this.memberId = memberId;
		ShareTitle = shareTitle;
		ShareContent = shareContent;
		ShareRegDate = shareRegDate;
		ShareProductStatus = shareProductStatus;
		ShareCategory = shareCategory;
		ShareState = shareState;
		StyleNo = styleNo;
		this.attachNo = attachNo;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
	}
	
	//get set 
	public int getShareNo() {
		return ShareNo;
	}

	public void setShareNo(int shareNo) {
		ShareNo = shareNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getShareTitle() {
		return ShareTitle;
	}

	public void setShareTitle(String shareTitle) {
		ShareTitle = shareTitle;
	}

	public String getShareContent() {
		return ShareContent;
	}

	public void setShareContent(String shareContent) {
		ShareContent = shareContent;
	}

	public Date getShareRegDate() {
		return ShareRegDate;
	}

	public void setShareRegDate(Date shareRegDate) {
		ShareRegDate = shareRegDate;
	}

	public String getShareProductStatus() {
		return ShareProductStatus;
	}

	public void setShareProductStatus(String shareProductStatus) {
		ShareProductStatus = shareProductStatus;
	}

	public String getShareCategory() {
		return ShareCategory;
	}

	public void setShareCategory(String shareCategory) {
		ShareCategory = shareCategory;
	}

	public String getShareState() {
		return ShareState;
	}

	public void setShareState(String shareState) {
		ShareState = shareState;
	}

	public Style getStyleNo() {
		return StyleNo;
	}

	public void setStyleNo(Style styleNo) {
		StyleNo = styleNo;
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

	@Override
	public String toString() {
		return "ShareBoardAndAttachment [ShareNo=" + ShareNo + ", memberId=" + memberId + ", ShareTitle=" + ShareTitle
				+ ", ShareContent=" + ShareContent + ", ShareRegDate=" + ShareRegDate + ", ShareProductStatus="
				+ ShareProductStatus + ", ShareCategory=" + ShareCategory + ", ShareState=" + ShareState + ", StyleNo="
				+ StyleNo + ", attachNo=" + attachNo + ", originalFilename=" + originalFilename + ", renamedFilename="
				+ renamedFilename + "]";
	}
	
	
	
	
	
}
