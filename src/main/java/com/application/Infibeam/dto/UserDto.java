package com.application.Infibeam.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import com.application.Infibeam.validations.PhoneNumber;

public class UserDto {
	
	private int userId;
	
	@NotEmpty(message="Username can not be empty")
	private String userName;
	
	@NotEmpty(message="EmailId can not be empty")
	@Email(message="Enter the correct Email address")
	private String emailId;
	
	@NotEmpty(message="Password can not be empty")
	@Size(min=6,message="password must be greater than 6 digit")
	private String password;
	
	@NotEmpty(message="Address can not be empty")
	private String address;
	
	@PhoneNumber
	private String mobile;
	Date createdAt;
	Date updatedAt;
	
	private String newPassword;
	
	private int roleId;
	
	private Set<String> roles;

	
	public UserDto() {}
	
	
	


	

	public UserDto(int userId,String userName,
			 String emailId,
			 String password,
			String address, String mobile, String newPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.address = address;
		this.mobile = mobile;
		this.newPassword = newPassword;
	}




	


	







	public int getRoleId() {
		return roleId;
	}







	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}







	public String getNewPassword() {
		return newPassword;
	}



	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}



	
	
	public Set<String> getRoles() {
		return roles;
	}







	public void setRoles(Set<String> roles) {
		this.roles = roles;
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

	
	

}
