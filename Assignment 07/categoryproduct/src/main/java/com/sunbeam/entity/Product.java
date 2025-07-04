package com.sunbeam.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 
 * */

@Entity
@Table(name="products",uniqueConstraints 
= @UniqueConstraint
(columnNames = {"name"}))
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude = "categoryId")

public class Product extends BaseEntity {
 
	@Column(length = 20,unique = true)
	private String name;
	
	@Column(name="product_description",length = 300)
	private String productDescription;
	
	@Column(name="manufacture_date")
	private LocalDate manufactureDate;
	
	private double price;
	
	@Column(name="available_quantity")
	private int availableQuantity;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category categoryId;

	public Product(String name, String productDescription, LocalDate manufactureDate, double price,
			int availableQuantity) {
		super();
		this.name = name;
		this.productDescription = productDescription;
		this.manufactureDate = manufactureDate;
		this.price = price;
		this.availableQuantity = availableQuantity;
	}
	
	
	
	
	
	
	
}
