package com.spring.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.data.jpa.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query(value = "SELECT * FROM Products  WHERE Product_Name = ?",nativeQuery=true)
	List<Product> findByProductName(String productName);
	
	
	@Query(value = "SELECT p FROM Product p ORDER BY price")
	public List<Product> SortedByPrice();		  
	   
	@Query(value = "SELECT p FROM Product p ORDER BY name")
	public List<Product> SortedByName();
	   
	@Query("from Product" )
	List<Product> findProducts(Pageable pageable);

} 

