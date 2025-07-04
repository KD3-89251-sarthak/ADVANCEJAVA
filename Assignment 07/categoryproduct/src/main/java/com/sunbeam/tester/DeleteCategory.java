package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.CategoryDao;
import com.sunbeam.dao.CategoryDaoImpl;
import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.utils.HibernateUtils;

public class DeleteCategory {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = HibernateUtils.getFactory()) {
			
			CategoryDao dao = new CategoryDaoImpl();
			System.out.println("Enter Category id ");
			Long categoryId = Long.valueOf(sc.nextLine());
			System.out.println(dao.deleteCategory(categoryId));
		}
	}
}
