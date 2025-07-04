package com.sunbeam.tester;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.utils.HibernateUtils;

public class GetPoductDetailsById {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getFactory(); Scanner sc = new Scanner(System.in)) {
			// 1. create dao instance
			ProductDao dao=new ProductDaoImpl();
			
			System.out.println("Enter Product id ");
			// 2. call dao's method
			System.out.println(dao.getProductDetailsById(sc.nextLong()));
		} /*
			 * JVM calls - sf.close() => DBCP is closed
			 */
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
