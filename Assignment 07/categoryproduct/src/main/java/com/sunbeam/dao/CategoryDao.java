package com.sunbeam.dao;

import com.sunbeam.entity.Category;

public interface CategoryDao {
	String addCategory(Category category);
	String deleteCategory(Long categoryID);
	
}
