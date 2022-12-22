package com.spring.data.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@Column(name = "Product_Number", nullable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Product_name", nullable = false, length = 40)
	private String name;
	
	@Column(name = "Product_price")
	private double price;
	
	@Column(name = "Units_In_Stock")
	private int unitsInStock;
	
	@Column(name = "Discontinue")
	private boolean discontinued;
	
	@ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER )
	@JoinColumn(name = "category_Number")
	private Category category;

	public Product() {
		super();
	}

	public Product(int id, String name, double price, int unitsInStock, boolean discontinued, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.unitsInStock = unitsInStock;
		this.discontinued = discontinued;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "\nProduct [id=" + id + ", name=" + name + ", price=" + price + ", unitsInStock=" + unitsInStock
				+ ", discontinued=" + discontinued + ", category=" + category + "]";
	}

}
