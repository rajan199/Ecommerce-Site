package com.application.Infibeam.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"createdat","updatedat"},allowGetters=true)
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(unique=true)
	private String userName;
	
	@Column(unique=true)
	private String emailId;
	
	@Column(length=100)
	private String password;
	
	
	private String address;
	
	@Column(length=15)
	private String mobile;
	
	@Column(nullable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	Date createdAt;
	
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	Date updatedAt;
	
	
	   @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	   @JoinTable(name = "user_roles", 
	    joinColumns = @JoinColumn(name = "userid"), 
	    inverseJoinColumns = @JoinColumn(name = "roleid"))
	    private Set<Role> roles = new HashSet();
	

	public Users() {}
	
	


	public Users(int userId, String userName, String emailId, String password, String address, String mobile) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.address = address;
		this.mobile = mobile;
	}




	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
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


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	
	

}
