package com.application.Infibeam.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.Infibeam.dto.CategoryDto;
import com.application.Infibeam.dto.ProductDto;
import com.application.Infibeam.dto.UserDto;
import com.application.Infibeam.model.Users;
import com.application.Infibeam.model.Category;
import com.application.Infibeam.model.Product;
import com.application.Infibeam.reposatory.CategoryRepo;
import com.application.Infibeam.reposatory.ProductRepo;
import com.application.Infibeam.reposatory.UserRepo;
import com.application.Infibeam.response.ResponseMessage;
import com.application.Infibeam.service.ImageProcess;
import com.application.Infibeam.validations.ProductValidator;

@CrossOrigin
@RestController
@RequestMapping("/infi")
public class ProductController {
	
	@Autowired
	ProductRepo productRepo;
	
	 	
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	UserRepo userRepo;
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//	    binder.setValidator(productValidator);
//	}

	
	@PostMapping(value="/addProduct",headers="Accept=application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VENDOR')")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto pdto){
		
//		productValidator.validate(pdto, result);  this one is the example of validator
//		if(result.hasErrors()) {
//			System.out.println("Error");
//			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
//}
//		
	
		
		Optional<Category> cat=categoryRepo.findById(pdto.getCatId());
		
		Optional<Users> user=userRepo.findById(pdto.getUserId());
		
		Product saveProduct=new Product(pdto.getProductId(), pdto.getProductName(), pdto.getProductImg(), pdto.getProductDesc(), pdto.getProductPrice(),cat.get(),user.get());
		
		try {
			
			String savePath=ImageProcess.getPath(pdto.getProductImg());    		        
	        
	        saveProduct.setProductImg(savePath);
	    	productRepo.save(saveProduct);
	    	
		} catch (Exception e) {
			
			e.printStackTrace();
		}  	
		
		
		return new ResponseEntity(new ResponseMessage("Product is added"), HttpStatus.CREATED);
    }
	
	
	
	@GetMapping(value="/getAllProduct",produces="application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('VENDOR')")
	public ResponseEntity<List<Product>> getAllProduct() throws IOException{ 
		 List<Product> list=productRepo.findAll();
		 List<Product> mainlist=new ArrayList<Product>();
		 for(Product p:list) {
			 
			 
			 File f=new File(p.getProductImg());
			 String encodstring = ImageProcess.encodeFileToBase64Binary(f);
			 p.setProductImg(encodstring);
			 p.setCategory(p.getCategory());
			 mainlist.add(p);
		}
		
		  return new ResponseEntity<List<Product>>(mainlist,HttpStatus.OK);
	 }  
	
	
	
	@GetMapping(value="/getById/{productId}")
	 @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('VENDOR')")
	public ResponseEntity<ProductDto> edit(@PathVariable("productId") int id){ 
		 
		  
		  Optional<Product> p=productRepo.findById(id);
		  ProductDto productDto=new ProductDto();
		  File f=new File(p.get().getProductImg());
		  String encodeString=ImageProcess.encodeFileToBase64Binary(f);
		  String wholestring="data:image/jpg;base64,"+encodeString;
		  productDto.setProductId(p.get().getProductId());
		  productDto.setProductName(p.get().getProductName());
		  productDto.setProductPrice(p.get().getProductPrice());
		  productDto.setProductDesc(p.get().getProductDesc());
		  productDto.setProductImg(p.get().getProductImg());
		  productDto.setTempImg(p.get().getProductImg());
		  productDto.setSingleImg(wholestring);
		  productDto.setCategory(p.get().getCategory());
		  
			 
		  return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	 } 
	
	
	@PutMapping(value="/updateProduct",produces="application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('VENDOR')")
	public ResponseEntity<Product> updateProduct(@RequestBody ProductDto pdto){
			
		System.out.println("cat id is "+pdto.getCatId());
		Optional<Category> cat=categoryRepo.findById(pdto.getCategory().getCatId());
		Optional<Users> user=userRepo.findById(pdto.getUserId());
			Product updateProduct=new Product(pdto.getProductId(), pdto.getProductName(), pdto.getProductImg(), pdto.getProductDesc(), pdto.getProductPrice(),cat.get(),user.get());
		
			 String str="";
				try {
					if(pdto.getProductImg().startsWith("data")) {
						str=pdto.getProductImg();	
						String savePath=ImageProcess.getPath(str);
						updateProduct.setProductImg(savePath);
				        String path=pdto.getTempImg();
						Path file=Paths.get(path);
						Files.deleteIfExists(file);
					}
					
					
			
				} catch (Exception e) {
					
					e.printStackTrace();
				} 
			
			productRepo.save(updateProduct);
			return new ResponseEntity<Product>(updateProduct, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value="/deleteProduct/{productId}",produces="application/json")
	 @PreAuthorize(" hasRole('ADMIN') or hasRole('VENDOR')")
	public ResponseEntity<?> deleteProduct(@PathVariable("productId") int id) throws IOException{
		
		if (!productRepo.findById(id).isPresent()) {
            
			return new ResponseEntity(new ResponseMessage("Product is not exist"), HttpStatus.BAD_REQUEST);
        }
		else {
			
			 Optional<Product> p=productRepo.findById(id);
			
			 String path=p.get().getProductImg();
			 Path file=Paths.get(path);
			 Files.deleteIfExists(file);
			 productRepo.deleteById(id);
			 
		     return new ResponseEntity<String>("Product is Deleted", HttpStatus.NO_CONTENT);
		}
	}
	
	 @GetMapping(value="/GetDataByCat/{catId}",produces="application/json")
	 @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('VENDOR')")
	public ResponseEntity<?> GetDataByCat(@PathVariable("catId") int id) throws IOException{
		
		 	List<Product> allData=productRepo.getAllByCat(id);
		 	 List<Product> mainlist=new ArrayList<Product>();
			 for(Product p:allData) {
				 
				 
				 File f=new File(p.getProductImg());
				 String encodstring = ImageProcess.encodeFileToBase64Binary(f);
				 p.setProductImg(encodstring);
				 mainlist.add(p);
			}
			
		 	
			 
		     return new ResponseEntity<List<Product>>(mainlist, HttpStatus.OK);
		}
	
}
