package com.sh.obtg.share.model.dto;

import java.sql.Date;
import java.util.List;

public class NshareAttachment {
	
	private int productAttachmentNo;
	private int productId; //게시글 번호
	private String originalFilename;
	private String renamedFilename;
	private Date regDate;

	
	
	
	//기본
	public NshareAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//매개변수
	public NshareAttachment(int productAttachmentNo, int productId, String originalFilename,
			String renamedFilename, Date regDate) {
		super();
		this.productAttachmentNo = productAttachmentNo;
		this.productId = productId;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.regDate = regDate;
	}
	
	
	
	//getset 
	public int getProductAttachmentNo() {
		return productAttachmentNo;
	}
	public void setProductAttachmentNo(int productAttachmentNo) {
		this.productAttachmentNo = productAttachmentNo;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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

	//tostring
	@Override
	public String toString() {
		return "NshareAttachment [productAttachmentNo=" + productAttachmentNo + ", productId=" + productId
				+ ", originalFilename=" + originalFilename + ", renamedFilename=" + renamedFilename + ", regDate="
				+ regDate + "]";
	}


	
	

}
