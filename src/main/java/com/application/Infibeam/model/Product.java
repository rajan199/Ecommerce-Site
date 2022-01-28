package com.application.Infibeam.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"createdat","updatedat"},allowGetters=true)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	private String productName;
	private String productImg;
	private String productDesc;
	
	@Column(precision=10,scale=2)
	private BigDecimal productPrice;
	
	@Column(nullable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	Date createdAt;
	
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="catId")
	private Category category;
	
	

	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="userId")
	private Users user;

	public Product() {}
	

	



	public Product(int productId, String productName, String productImg, String productDesc, BigDecimal productPrice,
			Category category, Users user) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productImg = productImg;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.category = category;
		this.user = user;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}






	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
