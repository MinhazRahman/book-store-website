package com.bookstore.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
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
	
	@Test
	public void testUpdateUsers() {
		//create an instance of Users
		Users user = new Users();
		Users updatedUser;
		
		//update user information
		user.setUserId(30);
		user.setEmail("test_update@example.com");
		user.setFullName("Jeffry Thompson");
		user.setPassword("password");
		
		updatedUser = userDAO.update(user);
		
		String expected = updatedUser.getPassword();
		String actual = "password";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 30;
		Users user = userDAO.get(userId);
		
		String expectedEmail = "test_update@example.com";
		String actualEmail = user.getEmail();
		
		assertNotNull(user);
		assertEquals(expectedEmail, actualEmail);	
		
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 100;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 29;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteUsersNotPresent() {
		Integer userId = 99;
		userDAO.delete(userId);
	}

	@AfterClass
	public static void tearDown() {
		// close EntityManager and EntityManagerFactory instances
		entityManager.close();
		entityManagerFactory.close();
	}

}
