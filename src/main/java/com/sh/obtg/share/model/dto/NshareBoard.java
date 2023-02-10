package com.sh.obtg.share.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class NshareBoard extends NshareBoardEntity {
	
	private int attachCnt;  //실제테이블엔 없지만 필요해 -- 첨부파일 수 카운트  
	private List<NshareAttachment>  shareAttachments = new ArrayList<>();
	
	
	
	//기본
	public NshareBoard() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	//부모 + cnt
	public NshareBoard(int productId, Subcategory subcategoryId, String memberId, Style styleName, String productName,
			String productContent, int productPrice, Date productRegDate, String productStatus, String productColor,
			int productReadCount, String productGender, String productQuality, int attachCnt) {
		super(productId, subcategoryId, memberId, styleName, productName, productContent, productPrice, productRegDate,
				productStatus, productColor, productReadCount, productGender, productQuality);
		this.attachCnt = attachCnt;
	}


	//부모 + att
	public NshareBoard(int productId, Subcategory subcategoryId, String memberId, Style styleName, String productName,
			String productContent, int productPrice, Date productRegDate, String productStatus, String productColor,
			int productReadCount, String productGender, String productQuality,
			List<NshareAttachment> shareAttachments) {
		super(productId, subcategoryId, memberId, styleName, productName, productContent, productPrice, productRegDate,
				productStatus, productColor, productReadCount, productGender, productQuality);
		this.shareAttachments = shareAttachments;
	}


	public int getAttachCnt() {
		return attachCnt;
	}


	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}


	public List<NshareAttachment> getShareAttachments() {
		return shareAttachments;
	}


	public void setShareAttachments(List<NshareAttachment> shareAttachments) {
		this.shareAttachments = shareAttachments;
	}


	
	
    @Override
	public String toString() {
		return "NshareBoard [attachCnt=" + attachCnt + ", shareAttachments=" + shareAttachments + ", getProductId()="
				+ getProductId() + ", getSubcategoryId()=" + getSubcategoryId() + ", getMemberId()=" + getMemberId()
				+ ", getStyleName()=" + getStyleName() + ", getProductName()=" + getProductName()
				+ ", getProductContent()=" + getProductContent() + ", getProductPrice()=" + getProductPrice()
				+ ", getProductRegDate()=" + getProductRegDate() + ", getProductStatus()=" + getProductStatus()
				+ ", getProductColor()=" + getProductColor() + ", getProductReadCount()=" + getProductReadCount()
				+ ", getProductGender()=" + getProductGender() + ", getProductQuality()=" + getProductQuality()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}



	public void addAttachment( NshareAttachment attah) {
    	this.shareAttachments.add(attah);
    }

	
	
}
