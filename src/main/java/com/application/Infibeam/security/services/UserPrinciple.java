package com.application.Infibeam.security.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.application.Infibeam.model.Users;

public class UserPrinciple implements UserDetails{

private int userId;
	
	private String userName;
	private String emailId;
	private String password;
	private String address;
	private String mobile;
	Date createdAt;
	Date updatedAt;
	 private Collection<? extends GrantedAuthority> authorities;
	 
	 
	 
	
	
	
	public UserPrinciple(int userId, String userName, String emailId, String password, String address, String mobile,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.address = address;
		this.mobile = mobile;
		this.authorities = authorities;
	}


	public static UserPrinciple build(Users user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().
        		map(role ->new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
 
        return new UserPrinciple(
                user.getUserId(),
                user.getUserName(),
                user.getEmailId(),
                user.getPassword(),
                user.getAddress(),
                user.getMobile(),
                authorities
                
        		);
    }

	
	public int getUserid() {
		return userId;
	}


	


	public String getEmailId() {
		return emailId;
	}


	public String getAddress() {
		return address;
	}


	public String getMobile() {
		return mobile;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
