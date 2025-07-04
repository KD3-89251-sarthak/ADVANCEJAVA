package com.sunbeam.tester;

import org.hibernate.SessionFactory;

import com.sunbeam.utils.HibernateUtils;

public class TestHibernate {

	public static void main(String[] args) {
		try(SessionFactory sf=HibernateUtils.getFactory()) {
			
			System.out.println("hibernate is running");
			
		}catch (Exception e) {
			
			// TODO: handle exception
		   e.printStackTrace();
		}
	}
}
