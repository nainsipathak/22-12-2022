package com.spring.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.data.jpa.exception.ProductNotFoundException;
import com.spring.data.jpa.model.Product;
import com.spring.data.jpa.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/p1/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
		//Get all Product
		@GetMapping
		public List<Product> getAll(){
			return productRepository.findAll();
		}
		
		//Add Product
		@PostMapping
		public Product save(@RequestBody Product product) {
			return productRepository.save(product);
		}
		

		//Find By Id
	    @GetMapping("/getbyid/{id}")
	    public ResponseEntity<Product> getById(@PathVariable  int id) throws ProductNotFoundException {
	        Product product = productRepository.findById(id)
	                .orElseThrow(() -> new ProductNotFoundException ("Product not exist with id :" + id));
	        
	        return ResponseEntity.ok(product);
	    }
	    
	  //Find the Product By name
	    @GetMapping("/getbyname/{productName}")
	    public ResponseEntity<List<Product>> findByProductName(@PathVariable  String productName){
	    	  List<Product> products = productRepository.findByProductName(productName);

	    	    if ( products.isEmpty()) {
	    	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	    }
	    	    
	    	    return new ResponseEntity<>( products, HttpStatus.OK);
	    	  }
	    	
	    //Sort the Product Price
	    @GetMapping("/sortbyprice")
	    public List<Product> getsortedByPrice(@RequestBody Product productdetails)  {
	        return productRepository.SortedByPrice();
	             
	    }
	    
	    //Sort the Product List
	    @GetMapping("/sortbyname")
	    public List<Product> getSortedByName(@RequestBody Product productdetails)  {
	        return productRepository.SortedByName();
	             
	    }
	    
	    //Get Products records by page
	    @GetMapping("/getbypage")
	    public ResponseEntity<List<Product>> getProducts() throws ProductNotFoundException {
			org.springframework.data.domain.Pageable pageable = PageRequest.of(0, 5);
			List<Product> list = productRepository.findProducts(pageable);
			
			if(list.size()<=0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return new ResponseEntity<List<Product>>(productRepository.findProducts(pageable), HttpStatus.OK);
		}
	    
	    //Update Product
	    @PutMapping("{id}")
	  	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable int id) {
	  		
	  		try{
	  			product.setId(id);
	  			
	  			productRepository.save(product);
	  		
	  			return ResponseEntity.ok().body(product);
	  			
	  		}
	  		
	  		catch(Exception e) {
	  			e.printStackTrace();
	  			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	  		}
	  		
	  	}
	    
	    
	    //Delete By Id
	    @DeleteMapping("{id}")
	    public ResponseEntity<HttpStatus> deleteById(@PathVariable int id) throws ProductNotFoundException {

	        Product product =  productRepository.findById(id)
	                .orElseThrow(() -> new ProductNotFoundException ("Product not exist with id : " + id));

	        productRepository.delete(product);

	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	    }	
	}
