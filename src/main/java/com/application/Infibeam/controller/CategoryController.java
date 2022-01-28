package com.application.Infibeam.controller;

import java.util.ArrayList;
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

import com.application.Infibeam.dto.CategoryDto;
import com.application.Infibeam.model.Category;
import com.application.Infibeam.reposatory.CategoryRepo;
import com.application.Infibeam.security.services.ResponseMessage;

@CrossOrigin
@RequestMapping("/infi")
@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@PostMapping(value="/addCategory",headers="Accept=application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VENDOR')")
	public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDto cdto){
		
		
		
		Category saveCat=new Category(cdto.getCatName());
		
		categoryRepo.save(saveCat);
		
		return new ResponseEntity(new ResponseMessage("Category is added"),HttpStatus.CREATED);
		
	}
	
	
	@GetMapping(value="/getAllCategory",produces="application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('VENDOR')")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<Category> list=categoryRepo.findAll();
		List<CategoryDto> mainlist=new ArrayList<CategoryDto>();
		
		for(Category c:list){
			CategoryDto cdto=new CategoryDto();
			Optional<Category> cat=categoryRepo.findById(c.getCatId());
			int count=cat.get().getProducts().size();
			cdto.setCatId(cat.get().getCatId());
			cdto.setCatName(cat.get().getCatName());
			cdto.setCnt(count);
			cdto.setProducts(c.getProducts());
		
			mainlist.add(cdto);
			
		}
		return new ResponseEntity<List<CategoryDto>>(mainlist,HttpStatus.OK);
	}
	
	@GetMapping(value="/getByCatId/{catid}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('VENDOR')")
	public ResponseEntity<Category> getByCatId(@PathVariable("catid")int id){
		
		Optional<Category> cat=categoryRepo.findById(id);
		
		return new ResponseEntity<Category>(cat.get(),HttpStatus.OK);
		
	}
	
	
	@PutMapping(value="/updateCategory",produces="application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryDto cdto){
		
		Category updateCat=new Category(cdto.getCatId(), cdto.getCatName());
		categoryRepo.save(updateCat);
		
		return new ResponseEntity<Category>(updateCat,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping(value="/deleteCategory/{catid}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteCategory(@PathVariable("catid") int id){
		
		categoryRepo.deleteById(id);
		
		return new ResponseEntity(new ResponseMessage("Category is Deleted"),HttpStatus.OK);
	}
	
	
}
