package com.sunbeam.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name="category") 
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = "")
public class Category extends BaseEntity{
  
	@Column(length = 50 ,unique = true)
	private String name;

	@Column(length = 255)
	private String description;
	
	
	@OneToMany(mappedBy = "categoryId",
			cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();

	
	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	
	public void addProduct(Product product) {
		this.products.add(product);// category  -> product
		product.setCategoryId(this);// product -> category
	}
	
	public void deleteProduct(Product product) {
		this.products.remove(product);// category  -> *product
		product.setCategoryId(null);// product -> category==null
	}
	
	
	

}
