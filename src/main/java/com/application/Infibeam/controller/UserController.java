package com.application.Infibeam.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.Infibeam.dto.LoginDto;
import com.application.Infibeam.dto.ProductDto;
import com.application.Infibeam.dto.UserDto;
import com.application.Infibeam.exception.ErrorResponse;
import com.application.Infibeam.exception.UserException;
import com.application.Infibeam.model.Category;
import com.application.Infibeam.model.Product;
import com.application.Infibeam.model.Role;
import com.application.Infibeam.model.RoleName;
import com.application.Infibeam.model.Users;
import com.application.Infibeam.reposatory.RoleRepository;
import com.application.Infibeam.reposatory.UserRepo;
import com.application.Infibeam.response.ResponseMessage;
import com.application.Infibeam.security.jwt.JwtProvider;
import com.application.Infibeam.security.jwt.JwtResponse;
import com.application.Infibeam.service.ImageProcess;

@CrossOrigin
@RestController
@RequestMapping("/infi")
public class UserController {

	@Autowired
	UserRepo userRepo;
	
	 @Autowired
	 AuthenticationManager authenticationManager;
	 
	  @Autowired
	  RoleRepository roleRepository;
	 
	  @Autowired
	  PasswordEncoder encoder;
	 
	  @Autowired
	  JwtProvider jwtProvider;
	  
	
	@PostMapping(value="/register",headers="Accept=application/json")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto user){
	
		if (userRepo.existsByUserName(user.getUserName())) {
		    throw new UserException("Fail -> Username is already taken!");
		 }
		 if (userRepo.existsByEmailId(user.getEmailId())) {
		     throw new UserException("Fail -> Emailid is already taken!");
		  }
		
		 System.out.println("role name is "+user.getRoleId());
		Users saveuser=new Users(user.getUserId(), user.getUserName(), user.getEmailId(), encoder.encode(user.getPassword()), user.getAddress(),user.getMobile());
		 Optional<Role> getrole=roleRepository.findById(user.getRoleId());
		// Set<String> strRoles = user.getRoles();
		 Set<Role> roles = new HashSet<Role>();
		
		 roles.add(getrole.get());
		 
//		 strRoles.forEach(role -> {
//		      switch (role) {
//		      case "admin":
//		        Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");
//		                 roles.add(adminRole.get());
//		               //  System.out.println("roles");
//		        break;
//		        
//		      case "vendor":
//		    	  Optional<Role> vendorRole = roleRepository.findByName("ROLE_VENDOR");
//              	  roles.add(vendorRole.get());
//              	  break;
//		     
//		      default:
//		        Optional<Role> userRole = roleRepository.findByName("ROLE_user");
//		        roles.add(userRole.get());
//		        break;
//		      }
//		    });
		 
		 saveuser.setRoles(roles);
		 
		 System.out.println("Roles are"+roles);
		userRepo.save(saveuser);
		
		return new ResponseEntity(new ResponseMessage("User is Registered succesfully"), HttpStatus.CREATED);
    }
	
	
	@PostMapping(value="/login",headers="Accept=application/json")
	public ResponseEntity<?> login_data(@RequestBody LoginDto log) throws NoSuchAlgorithmException, NoSuchProviderException{
		
		Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(log.getUserName(), log.getPassword()));
		 
		    SecurityContextHolder.getContext().setAuthentication(authentication);
		 
		
		    String jwt = jwtProvider.generateJwtToken(authentication);
		    
		  //  System.out.println("This is from the login"+jwt);
		    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		 
		    System.out.println("Inside login controller");
		    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
		
	}
	
	@GetMapping(value="/FindByUserName/{userName}",headers="Accept=application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> FindByUserName(@PathVariable("userName")String userName){
		Optional<Users> user=userRepo.findByUserName(userName);
		
		return new ResponseEntity<Users>(user.get(),HttpStatus.OK);
	}
	
	
	@PutMapping(value="/updateUser",produces="application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto user){
			
		
		
		Users saveuser=new Users(user.getUserId(), user.getUserName(), user.getEmailId(), encoder.encode(user.getPassword()), user.getAddress(),user.getMobile());
		Date updateAt=new Date();
		System.out.println("Date is "+updateAt);
		user.setUpdatedAt(updateAt);
		
		userRepo.updateUser(user.getUserId(),user.getAddress(),user.getMobile(),user.getUpdatedAt());
				
		
		return new ResponseEntity(new ResponseMessage("User Profile is updated"), HttpStatus.CREATED);
	}
	
	
	@PutMapping(value="/changePassword",produces="application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> changePassword(@Valid @RequestBody UserDto user){
			
		
		Optional<Users> getUser=userRepo.findByUserName(user.getUserName());
		
		
		boolean result = encoder.matches(user.getPassword(), getUser.get().getPassword());
		if(result) {
			userRepo.changePassword(user.getUserId(), encoder.encode(user.getNewPassword()));
			return new ResponseEntity(new ResponseMessage("Password is updated"), HttpStatus.CREATED);

		}
		else {
			throw new UserException("Old Password is wrong");
		}
		
				
		}
	
	
		@GetMapping(value="/getAllUser")
		@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
		public ResponseEntity<?> getAllUSers(){
			List<Users> getall=userRepo.getAllUser();
			
			return new ResponseEntity(getall,HttpStatus.OK);
		}
	
	
	
	  @ExceptionHandler(UserException.class)	  
	  public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		
	        ErrorResponse error = new ErrorResponse();
	        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	        
	        error.setMessage(ex.getMessage());
	        
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	    }

	
	
	
	
	
	
}
