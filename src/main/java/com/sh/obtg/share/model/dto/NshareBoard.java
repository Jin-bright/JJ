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


	//부모 + att
	public NshareBoard(int product_id, Subcategory subcategory_id, String memberId, Style style_name,
			String product_name, String product_content, int product_price, Date product_reg_date,
			String product_status, String product_color, int product_read_count, String product_gender,
			String product_quality, List<NshareAttachment> shareAttachments) {
		super(product_id, subcategory_id, memberId, style_name, product_name, product_content, product_price,
				product_reg_date, product_status, product_color, product_read_count, product_gender, product_quality);
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


	
	//완전긴tostring..
	@Override
	public String toString() {
		return "NshareBoard [attachCnt=" + attachCnt + ", shareAttachments=" + shareAttachments + ", getProduct_id()="
				+ getProduct_id() + ", getSubcategory_id()=" + getSubcategory_id() + ", getMemberId()=" + getMemberId()
				+ ", getStyle_name()=" + getStyle_name() + ", getProduct_name()=" + getProduct_name()
				+ ", getProduct_content()=" + getProduct_content() + ", getProduct_price()=" + getProduct_price()
				+ ", getProduct_reg_date()=" + getProduct_reg_date() + ", getProduct_status()=" + getProduct_status()
				+ ", getProduct_color()=" + getProduct_color() + ", getProduct_read_count()=" + getProduct_read_count()
				+ ", getProduct_gender()=" + getProduct_gender() + ", getProduct_quality()=" + getProduct_quality()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

    public void addAttachment( NshareAttachment attah) {
    	this.shareAttachments.add(attah);
    }

	
	
}
