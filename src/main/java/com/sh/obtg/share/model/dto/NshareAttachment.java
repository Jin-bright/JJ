package com.sh.obtg.share.model.dto;

import java.sql.Date;

public class NshareAttachment {
	
	private int product_attachment_no;
	private int product_id; //게시글 번호
	private String original_filename;
	private String renamed_filename;
	private Date regDate;
	
	//기본
	public NshareAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//매개변수
	public NshareAttachment(int product_attachment_no, int product_id, String original_filename,
			String renamed_filename, Date regDate) {
		super();
		this.product_attachment_no = product_attachment_no;
		this.product_id = product_id;
		this.original_filename = original_filename;
		this.renamed_filename = renamed_filename;
		this.regDate = regDate;
	}
	
	//getset 
	public int getProduct_attachment_no() {
		return product_attachment_no;
	}
	public void setProduct_attachment_no(int product_attachment_no) {
		this.product_attachment_no = product_attachment_no;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getOriginal_filename() {
		return original_filename;
	}
	public void setOriginal_filename(String original_filename) {
		this.original_filename = original_filename;
	}
	public String getRenamed_filename() {
		return renamed_filename;
	}
	public void setRenamed_filename(String renamed_filename) {
		this.renamed_filename = renamed_filename;
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
		return "NshareAttachment [product_attachment_no=" + product_attachment_no + ", product_id=" + product_id
				+ ", original_filename=" + original_filename + ", renamed_filename=" + renamed_filename + ", regDate="
				+ regDate + "]";
	}
	
	
	

}
