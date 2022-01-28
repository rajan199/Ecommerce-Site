package com.application.Infibeam.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="category")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"createdat","updatedat"},allowGetters=true)
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int catId;
	
	private String catName;
	
	@Column(nullable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	Date createdAt;
	
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	Date updatedAt;

	@OneToMany(mappedBy="category",fetch=FetchType.LAZY)
	@JsonIgnore
	Set<Product> products=new HashSet<Product>();

	public Category() {}
	

	public Category(String catName) {
		super();
		
		this.catName = catName;
	}
	
	


	public Category(int catId, String catName) {
		super();
		this.catId = catId;
		this.catName = catName;
	}


	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}


	public int getCatId() {
		return catId;
	}


	public void setCatId(int catId) {
		this.catId = catId;
	}


	public String getCatName() {
		return catName;
	}


	public void setCatName(String catName) {
		this.catName = catName;
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
