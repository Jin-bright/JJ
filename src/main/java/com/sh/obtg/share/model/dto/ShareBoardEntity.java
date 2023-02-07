package com.sh.obtg.share.model.dto;

import java.sql.Date;


public class ShareBoardEntity {
	private int ShareNo;
	private String memberId;
	private String ShareTitle;
	private String ShareContent;
	private int ShareReadCount;
	private Date ShareRegDate;
	private Date ShareBuyDate;
	private String ShareProductStatus;
	private String ShareCategory;
	private String ShareState; //거래전 - 거래중 - 거래완료
	private Style StyleNo; // style	varchar2(20) NOT NULL,
	
	//기본생성자
	public ShareBoardEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	//list 뽑을거 
	/**
	 * shareBoard.setShareState("SHARE_STATE");
						shareBoard.setShareTitle("SAHRE_TITLE");
						shareBoard.setShareCategory("SHARE_CATEGORY");
						shareBoard.setShareBuyDate("SHARE_BUY_DATE");
						shareBoard.setShareProductStatus("SHARE_PRODUCT_STATUS");
						
	 * @return
	 */
	public ShareBoardEntity(String shareTitle, Date shareBuyDate, String shareProductStatus, String shareCategory,
			String shareState) {
		super();
		ShareTitle = shareTitle;
		ShareBuyDate = shareBuyDate;
		ShareProductStatus = shareProductStatus;
		ShareCategory = shareCategory;
		ShareState = shareState;
	}

	

	public ShareBoardEntity(int shareNo, String memberId, String shareTitle, String shareContent, int shareReadCount,
			Date shareRegDate, Date shareBuyDate, String shareProductStatus, String shareCategory, String shareState,
			Style styleNo) {
		super();
		this.ShareNo = shareNo;
		this.memberId = memberId;
		this.ShareTitle = shareTitle;
		this.ShareContent = shareContent;
		this.ShareReadCount = shareReadCount;
		this.ShareRegDate = shareRegDate;
		this.ShareBuyDate = shareBuyDate;
		this.ShareProductStatus = shareProductStatus;
		this.ShareCategory = shareCategory;
		this.ShareState = shareState;
		this.StyleNo = styleNo;
		
		
		
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

	public int getShareReadCount() {
		return ShareReadCount;
	}

	public void setShareReadCount(int shareReadCount) {
		ShareReadCount = shareReadCount;
	}

	public Date getShareRegDate() {
		return ShareRegDate;
	}

	public void setShareRegDate(Date shareRegDate) {
		ShareRegDate = shareRegDate;
	}

	public Date getShareBuyDate() {
		return ShareBuyDate;
	}

	public void setShareBuyDate(Date shareBuyDate) {
		ShareBuyDate = shareBuyDate;
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

	@Override
	public String toString() {
		return "ShareBoardEntity [ShareNo=" + ShareNo + ", memberId=" + memberId + ", ShareTitle=" + ShareTitle
				+ ", ShareContent=" + ShareContent + ", ShareReadCount=" + ShareReadCount + ", ShareRegDate="
				+ ShareRegDate + ", ShareBuyDate=" + ShareBuyDate + ", ShareProductStatus=" + ShareProductStatus
				+ ", ShareCategory=" + ShareCategory + ", ShareState=" + ShareState + ", StyleNo=" + StyleNo + "]";
	}
	
	
	
	


}


/**
CREATE TABLE SHARE_board (
SHARE_no	number,
member_id	varchar2(50)		NOT NULL,
SAHRE_TITLE	varchar2(50)		NOT NULL,
SAHRE_CONTENT	varchar2(4000)	NOT NULL,
SAHRE_READ_COUNT	number	default 0,
SAHRE_REG_DATE	Date		default sysdate,
SHARE_BUY_DATE	Date		NULL,
SHARE_PRODUCT_STATUS	varchar2(50)		NULL,
SHARE_CATEGORY	varchar2(50)		NULL,
SHARE_STATE	varchar2(50)		NULL,
style	varchar2(20)		NOT NULL,
CONSTRAINT PK_SHARE_BOARD PRIMARY KEY (SHARE_no),
CONSTRAINT FK_Member_TO_SHARE_board_1 FOREIGN KEY (member_id) REFERENCES Member (member_id) on delete set null,
CONSTRAINT FK_fashionstyle_TO_SHARE_board_1 FOREIGN KEY (style) REFERENCES fashionstyle (style_no) on delete set null
);
**/
