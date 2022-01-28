package com.application.Infibeam.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.Infibeam.model.Role;
import com.application.Infibeam.reposatory.RoleRepository;
import com.application.Infibeam.security.services.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/infi")
public class RoleController {
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping(value="/getAllRoles",headers="Accept=application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllRoles(){
		
		List<Role> roles=roleRepository.findAll();
		
		return new ResponseEntity<List<Role>>(roles,HttpStatus.OK);
	}
	
	
	@PostMapping(value="/addRole",headers="Accept=application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addRole(@Valid @RequestBody Role role){
		
		String preFix="ROLE_";
		String wholeString=preFix+role.getName().toUpperCase();
		//System.out.println("Whole String is "+wholeString);
		role.setName(wholeString);
		roleRepository.save(role);
		
		return new ResponseEntity(new ResponseMessage("Role is created"),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/deleteRole/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){
		
		roleRepository.deleteById(id);
		
		return new ResponseEntity(new ResponseMessage("Role is Deleted"),HttpStatus.OK);
	}
	
	
	@GetMapping(value="/getByIdRole/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Role> getByIdRole(@PathVariable("id")int id){
		
		Optional<Role> role=roleRepository.findById(id);
		
		Role r=role.get();
		
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
	
	@PutMapping(value="/updateRole")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateRole(@RequestBody Role role){
		
		String roles=role.getName();
		if(roles.startsWith("ROLE_")) {
			roleRepository.save(role);
		}
		else {
			String preFix="ROLE_";
			String wholeString=preFix+role.getName().toUpperCase();
			role.setName(wholeString);
			roleRepository.save(role);
		}

		
		return new ResponseEntity(new ResponseMessage("Role is updated Succesfully"),HttpStatus.OK);
	}
	
	@GetMapping(value="/getAllExceptAdmin")
	public ResponseEntity<?> getAllExceptAdmin(){
		List<Role> getAll=roleRepository.getAllRoles();
		
		return new ResponseEntity<List<Role>>(getAll,HttpStatus.OK);
	}

}
