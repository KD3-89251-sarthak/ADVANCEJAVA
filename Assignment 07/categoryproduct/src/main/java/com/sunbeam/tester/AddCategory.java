package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.CategoryDao;
import com.sunbeam.dao.CategoryDaoImpl;
import com.sunbeam.entity.Category;
import com.sunbeam.utils.HibernateUtils;

public class AddCategory {
 
	public static void main(String[] args) {
		try(SessionFactory sf=HibernateUtils.getFactory();
				Scanner sc=new Scanner(System.in)) {
			//1. create dao instance
			CategoryDao dao=new CategoryDaoImpl();
			
			System.out.println("Enter Category details - Name desciption ");
			//2. create Product 

			
		   Category cat=new Category(sc.next(),sc.next());
		
			//3. call dao's method
			System.out.println(dao.addCategory(cat));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
