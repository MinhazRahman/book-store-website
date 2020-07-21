package com.bookstore.dao;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUp() {
		// create a EntityManager instance from EntityManagerFactory
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();

		// create a user into the database
		userDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUsers() {
		// create Users instance
		Users user = new Users();
		Users returnedUser;

		user.setEmail("test3@example.com");
		user.setFullName("Miranda Wander");
		user.setPassword("mirandapass");

		returnedUser = userDAO.create(user);

		assertTrue(returnedUser.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		// create Users instance
		Users user = new Users();
		
		userDAO.create(user);
	}

	@AfterClass
	public static void tearDown() {
		// close EntityManager and EntityManagerFactory instances
		entityManager.close();
		entityManagerFactory.close();
	}

}
