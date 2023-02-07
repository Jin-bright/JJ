package com.sh.obtg.share.model.dto;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ShareBoard extends ShareBoardEntity {
	
	private int attachCnt;  //실제테이블엔 없지만 필요해 -- 첨부파일 수 카운트  
	private List<ShareAttachment> shareAttachments = new ArrayList<>();

	//기본 
	public ShareBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	// attachcont + 부모 
	public ShareBoard(int shareNo, String memberId, String shareTitle, String shareContent, int shareReadCount,
			Date shareRegDate, Date shareBuyDate, String shareProductStatus, String shareCategory, String shareState,
			Style styleNo, int attachCnt) {
		super(shareNo, memberId, shareTitle, shareContent, shareReadCount, shareRegDate, shareBuyDate,
				shareProductStatus, shareCategory, shareState, styleNo);
		this.attachCnt = attachCnt;
	}

	
	
	public ShareBoard(int shareNo, String memberId, String shareTitle, String shareContent, int shareReadCount,
			Date shareRegDate, Date shareBuyDate, String shareProductStatus, String shareCategory, String shareState,
			Style styleNo, List<ShareAttachment> shareAttachments) {
		super(shareNo, memberId, shareTitle, shareContent, shareReadCount, shareRegDate, shareBuyDate,
				shareProductStatus, shareCategory, shareState, styleNo);
		this.shareAttachments = shareAttachments;
	}

	
	
	//get set 
	public int getAttachCnt() {
		return attachCnt;
	}

	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}

	public List<ShareAttachment> getShareAttachments() {
		return shareAttachments;
	}

	public void setShareAttachments(List<ShareAttachment> shareAttachments) {
		this.shareAttachments = shareAttachments;
	}

	@Override
	public String toString() {
		return "ShareBoard [attachCnt=" + attachCnt + ", shareAttachments=" + shareAttachments + ", getShareNo()="
				+ getShareNo() + ", getMemberId()=" + getMemberId() + ", getShareTitle()=" + getShareTitle()
				+ ", getShareContent()=" + getShareContent() + ", getShareReadCount()=" + getShareReadCount()
				+ ", getShareRegDate()=" + getShareRegDate() + ", getShareBuyDate()=" + getShareBuyDate()
				+ ", getShareProductStatus()=" + getShareProductStatus() + ", getShareCategory()=" + getShareCategory()
				+ ", getShareState()=" + getShareState() + ", getStyleNo()=" + getStyleNo() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	//
	public void addAttachment(ShareAttachment attach) {
		this.shareAttachments.add(attach);
	}

	
	
	
}


