package com.sh.obtg.share.model.dto;

import java.sql.Date;

public class NshareBoardEntity {

	private int productId;
	private Subcategory subcategoryId; // enum 
	private String memberId;
	private Style styleName; // enum 
	private String productName;
	private String productContent;
	private int productPrice;
	private Date productRegDate;
	private String productStatus; //거래전 - 거래중 - 거래완료
	private String productColor;
	private int productReadCount;
	private String productGender; //남/녀
	private String productQuality; // 상 중 하 
	
	//기본생성자
	public NshareBoardEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//매개변수
	public NshareBoardEntity(int productId, Subcategory subcategoryId, String memberId, Style styleName,
			String productName, String productContent, int productPrice, Date productRegDate,
			String productStatus, String productColor, int productReadCount, String productGender,
			String productQuality) {
		super();
		this.productId = productId;
		this.subcategoryId = subcategoryId;
		this.memberId = memberId;
		this.styleName = styleName;
		this.productName = productName;
		this.productContent = productContent;
		this.productPrice = productPrice;
		this.productRegDate = productRegDate;
		this.productStatus = productStatus;
		this.productColor = productColor;
		this.productReadCount = productReadCount;
		this.productGender = productGender;
		this.productQuality = productQuality;
	}
	
	// GET SET
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Subcategory getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(Subcategory subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Style getStyleName() {
		return styleName;
	}
	public void setStyleName(Style styleName) {
		this.styleName = styleName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public Date getProductRegDate() {
		return productRegDate;
	}
	public void setProductRegDate(Date productRegDate) {
		this.productRegDate = productRegDate;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	public int getProductReadCount() {
		return productReadCount;
	}
	public void setProductReadCount(int productReadCount) {
		this.productReadCount = productReadCount;
	}
	public String getProductGender() {
		return productGender;
	}
	public void setProductGender(String productGender) {
		this.productGender = productGender;
	}
	public String getProductQuality() {
		return productQuality;
	}
	public void setProductQuality(String productQuality) {
		this.productQuality = productQuality;
	}

	@Override
	public String toString() {
		return "NshareBoardEntity [productId=" + productId + ", subcategoryId=" + subcategoryId + ", memberId="
				+ memberId + ", styleName=" + styleName + ", productName=" + productName + ", productContent="
				+ productContent + ", productPrice=" + productPrice + ", productRegDate=" + productRegDate
				+ ", productStatus=" + productStatus + ", productColor=" + productColor + ", productReadCount="
				+ productReadCount + ", productGender=" + productGender + ", productQuality=" + productQuality + "]";
	}
	
	
	
	
	
	
	
}
