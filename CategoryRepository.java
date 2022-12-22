package com.spring.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.data.jpa.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	
	@Query(value = "SELECT * FROM Categories  WHERE category_name = ?",nativeQuery=true)
	public Category getByName(String name);
	
	@Query(value = "SELECT * FROM Categories  WHERE category_name like 'Foo%'",nativeQuery=true)
	public List<Category> getByNames(String name);
	
	@Query(value = "SELECT c FROM Category c ORDER BY name")
	public List<Category> sortedByName();
	
	@Query("from Category" )
	List<Category> findCategories(Pageable pageable);
	
	 
} 

