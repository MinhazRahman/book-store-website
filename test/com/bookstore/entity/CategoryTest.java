package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

public class CategoryTest {
	
	@Test
	public void testUsersPersistence() {
		//create Category instance
		Category category = new Category();
		
		category.setName("Programming");
		
		//create a EntityManager instance from EntityManagerFactory
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//begin the transaction
		entityManager.getTransaction().begin();
		
		//persist the Category instance
		entityManager.persist(category);
		
		//commit the transaction
		entityManager.getTransaction().commit();
		
		//close EntityManager and EntityManagerFactory instances
		entityManager.close();
		entityManagerFactory.close();
			
	}

}
