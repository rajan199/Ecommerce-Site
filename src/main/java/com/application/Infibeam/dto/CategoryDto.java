package com.application.Infibeam.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.application.Infibeam.model.Product;

public class CategoryDto {
   
	
	private int catId;
	
	@NotEmpty(message="Category name can not be empty")
	private String catName;

	private int cnt;
	Set<Product> products=new HashSet<Product>();
	public CategoryDto() {}


	


	
	public CategoryDto(int catId,String catName, int cnt) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.cnt = cnt;
	}






	public Set<Product> getProducts() {
		return products;
	}






	public void setProducts(Set<Product> products) {
		this.products = products;
	}






	public int getCnt() {
		return cnt;
	}


	public void setCnt(int cnt) {
		this.cnt = cnt;
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
	
	
}
