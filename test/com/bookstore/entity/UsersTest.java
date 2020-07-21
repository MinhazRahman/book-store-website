package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

public class UsersTest {
	
	@Test
	public void testUsersPersistence() {
		//create Users instance
		Users user = new Users();
		user.setEmail("abc@example.com");
		user.setFullName("Kyle Walker");
		user.setPassword("kylepass");
		
		//create a EntityManager instance from EntityManagerFactory
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//begin the transaction
		entityManager.getTransaction().begin();
		
		//persist the Users instance
		entityManager.persist(user);
		
		//commit the transaction
		entityManager.getTransaction().commit();
		
		//close EntityManager and EntityManagerFactory instances
		entityManager.close();
		entityManagerFactory.close();
		
		
	}

}
