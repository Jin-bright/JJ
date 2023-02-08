package com.sh.obtg.share.model.dto;

import java.sql.Date;

public class NshareBoardEntity {

	private int product_id;
	private Subcategory subcategory_id; // enum 
	private String memberId;
	private Style style_name; // enum 
	private String product_name;
	private String product_content;
	private int product_price;
	private Date product_reg_date;
	private String product_status; //거래전 - 거래중 - 거래완료
	private String product_color;
	private int product_read_count;
	private String product_gender; //남/녀
	private String product_quality; // 상 중 하 
	
	//기본생성자
	public NshareBoardEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//매개변수
	public NshareBoardEntity(int product_id, Subcategory subcategory_id, String memberId, Style style_name,
			String product_name, String product_content, int product_price, Date product_reg_date,
			String product_status, String product_color, int product_read_count, String product_gender,
			String product_quality) {
		super();
		this.product_id = product_id;
		this.subcategory_id = subcategory_id;
		this.memberId = memberId;
		this.style_name = style_name;
		this.product_name = product_name;
		this.product_content = product_content;
		this.product_price = product_price;
		this.product_reg_date = product_reg_date;
		this.product_status = product_status;
		this.product_color = product_color;
		this.product_read_count = product_read_count;
		this.product_gender = product_gender;
		this.product_quality = product_quality;
	}
	
	// GET SET
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public Subcategory getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(Subcategory subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Style getStyle_name() {
		return style_name;
	}
	public void setStyle_name(Style style_name) {
		this.style_name = style_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public Date getProduct_reg_date() {
		return product_reg_date;
	}
	public void setProduct_reg_date(Date product_reg_date) {
		this.product_reg_date = product_reg_date;
	}
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}
	public String getProduct_color() {
		return product_color;
	}
	public void setProduct_color(String product_color) {
		this.product_color = product_color;
	}
	public int getProduct_read_count() {
		return product_read_count;
	}
	public void setProduct_read_count(int product_read_count) {
		this.product_read_count = product_read_count;
	}
	public String getProduct_gender() {
		return product_gender;
	}
	public void setProduct_gender(String product_gender) {
		this.product_gender = product_gender;
	}
	public String getProduct_quality() {
		return product_quality;
	}
	public void setProduct_quality(String product_quality) {
		this.product_quality = product_quality;
	}
	
	
	@Override
	public String toString() {
		return "NshareBoardEntity [product_id=" + product_id + ", subcategory_id=" + subcategory_id + ", memberId="
				+ memberId + ", style_name=" + style_name + ", product_name=" + product_name + ", product_content="
				+ product_content + ", product_price=" + product_price + ", product_reg_date=" + product_reg_date
				+ ", product_status=" + product_status + ", product_color=" + product_color + ", product_read_count="
				+ product_read_count + ", product_gender=" + product_gender + ", product_quality=" + product_quality
				+ "]";
	}
	
	
	
	
	
}
