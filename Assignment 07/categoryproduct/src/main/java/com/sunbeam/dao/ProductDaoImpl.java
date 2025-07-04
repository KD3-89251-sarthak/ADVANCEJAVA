package com.sunbeam.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeam.entity.Category;
import com.sunbeam.entity.Product;
import com.sunbeam.utils.HibernateUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public String addProduct(Product product, Long catId) {
		// TODO Auto-generated method stub
		String message = "adding product is failed";

		Session session = HibernateUtils.getFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		try {

			Category category = session.get(Category.class, catId);

			if (category != null) {
				// 4. not null => valid restaurant id =>exists
				category.addProduct(product);
				// 5. invoke persist
				// session.persist(foodItem); - no longer required , since added cascading
				message = "Product added successfully " ;
			}

			tx.commit();

		} catch (RuntimeException e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return message;
	}

	@Override
	public Product getProductDetailsById(Long productId) {
		// TODO Auto-generated method stub
		Product product = null;
		Session session = HibernateUtils.getFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		try {
			product = session.get(Product.class, productId);
			tx.commit();
		} catch (RuntimeException e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return product;
	}

	@Override
	public String addProduct(List<Product> products, Long catId) {
		String message = "adding product is failed";

		Session session = HibernateUtils.getFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		try {

			Category category = session.get(Category.class, catId);

			if (category != null) {
				

				products.forEach(product -> category.addProduct(product));
				message = "Product added successfull";
			}

			tx.commit();

		} catch (RuntimeException e) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return message;

	}
}
