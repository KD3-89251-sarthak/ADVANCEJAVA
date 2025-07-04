package com.sunbeam.dao;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeam.entity.Category;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public String addCategory(Category category) {
		// TODO Auto-generated method stub
		
		String mesg = "adding category failed !!!!!!!!!!!";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			session.persist(category);
			tx.commit();
			mesg = "added new category , ID" + category.getId();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return mesg;

	}

	@Override
	public String deleteCategory(Long categoryID) {
		
		String mesg = "deleting category failed !!!!!!!!!!!";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			Category category = session.get(Category.class, categoryID);

			session.remove(category);

			
			tx.commit();
			mesg = "deleting category sucessfully " ;
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return mesg;


	}
}
