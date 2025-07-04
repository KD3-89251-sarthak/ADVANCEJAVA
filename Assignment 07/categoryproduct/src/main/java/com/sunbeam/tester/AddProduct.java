package com.sunbeam.tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.entity.Product;
import com.sunbeam.utils.HibernateUtils;

public class AddProduct {

	public static void main(String[] args) {
		try(SessionFactory sf=HibernateUtils.getFactory();
				Scanner sc=new Scanner(System.in)) {
			//1. create dao instance
			ProductDao dao=new ProductDaoImpl();
			
			System.out.println("Enter Product details - Name, productDescription,manufactureDate, price,\r\n"
					+ " availableQuantity, Category ID ");
			//2. create Product 

			
			Product newProducr=new Product(sc.next(),sc.next(), LocalDate.parse(sc.next()), sc.nextDouble(), sc.nextInt());
		
			Long catId=sc.nextLong();
		
			//3. call dao's method
			System.out.println(dao.addProduct(newProducr, catId));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
