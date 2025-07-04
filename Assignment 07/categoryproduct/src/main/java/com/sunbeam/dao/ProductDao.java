package com.sunbeam.dao;

import java.util.List;

import com.sunbeam.entity.Product;

public interface ProductDao {

	String addProduct(Product product,Long catId);
	Product getProductDetailsById(Long productId);
	
	String addProduct(List<Product> products,Long catId);
	
	


	
}
