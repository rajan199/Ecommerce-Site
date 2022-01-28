package com.application.Infibeam.security.services;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.Infibeam.model.Users;
import com.application.Infibeam.reposatory.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepo userRepo;


	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Users> user=userRepo.findByUserName(username);
		
		Users users=new Users();
		if(user.isPresent()) {
			users=user.get();
		}
		else {
			throw new UsernameNotFoundException("User Not Found with -> username or email : " + username);
		}
		return UserPrinciple.build(users);
	}
	

	

}

