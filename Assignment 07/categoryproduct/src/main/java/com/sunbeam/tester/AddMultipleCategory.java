package com.sunbeam.tester;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.entity.Product;
import com.sunbeam.utils.HibernateUtils;

public class AddMultipleCategory {
 
	public static void main(String[] args) {
		try(SessionFactory sf=HibernateUtils.getFactory();
				Scanner sc=new Scanner(System.in)) {
			//1. create dao instance
			ProductDao dao=new ProductDaoImpl();
			
			//2. create Product 

			
			System.out.println("Enter category Id id ");
			Long catId = sc.nextLong();
			List<Product> products = new ArrayList<>();
			for (int i = 0; i < 3; i++) {

				System.out.println("Enter Product details - Name, productDescription,manufactureDate, price,\r\n"
						+ " availableQuantity ");
				Product newProducr=new Product(sc.next(),sc.next(), LocalDate.parse(sc.next()), sc.nextDouble(), sc.nextInt());
				
				products.add(newProducr);
			}
			
			
			
			
			
		
			//3. call dao's method
			System.out.println(dao.addProduct(products, catId));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
