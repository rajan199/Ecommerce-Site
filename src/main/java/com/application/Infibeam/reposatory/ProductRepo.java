package com.application.Infibeam.reposatory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.Infibeam.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query(value="select p.*,c.* from product as p,category as c where p.cat_id=c.cat_id",nativeQuery=true)
	List<Product> getAllJoinData();
	
	@Query("select p from Product p where p.category.catId=?1")
	List<Product> getAllByCat(int id);
}
