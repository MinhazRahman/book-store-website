package com.bookstore.dao;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {

	@Test
	public void testCreateUsers() {
		// create Users instance
		Users user = new Users();
		Users returnedUser;
		UserDAO userDAO;

		user.setEmail("abc@example.com");
		user.setFullName("Kyle Walker");
		user.setPassword("kylepass");

		// create a EntityManager instance from EntityManagerFactory
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// create a user into the database
		userDAO = new UserDAO(entityManager);
		returnedUser = userDAO.create(user);

		// close EntityManager and EntityManagerFactory instances
		entityManager.close();
		entityManagerFactory.close();
		
		assertTrue(returnedUser.getUserId() > 0);
	}

}
