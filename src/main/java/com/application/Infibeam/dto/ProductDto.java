package com.application.Infibeam.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.application.Infibeam.model.Category;

public class ProductDto {
	
	public int productId;
	
	@NotEmpty(message="Product name can not be empty")
	public String productName;
	
	@NotEmpty(message="Product Image can not be empty")
	public String productImg;
	
	@NotEmpty(message="Product Description can not be empty")
	public String productDesc;
	public String tempImg;
	public String singleImg;
	
	public int catId;
	
	public int userId;
	
	
	private Category category;
	
	@NotNull(message="Product Price can not be null")
	public BigDecimal productPrice;
	
	Date createdAt;
	Date updatedAt;
	
	public ProductDto() {}
	public ProductDto(int productId, String productName, String productImg, String productDesc, String tempImg,
			String singleImg, BigDecimal productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productImg = productImg;
		this.productDesc = productDesc;
		this.tempImg = tempImg;
		this.singleImg = singleImg;
		this.productPrice = productPrice;
		
	}
	
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getTempImg() {
		return tempImg;
	}
	public void setTempImg(String tempImg) {
		this.tempImg = tempImg;
	}
	public String getSingleImg() {
		return singleImg;
	}
	public void setSingleImg(String singleImg) {
		this.singleImg = singleImg;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
	
	
}
