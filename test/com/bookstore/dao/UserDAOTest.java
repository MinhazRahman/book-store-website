package com.bookstore.dao;


import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Users;

/* JUnit 4 test case */
public class UserDAOTest{
	
	private static UserDAO userDAO;

	@BeforeAll
	public static void setUp() {

		userDAO = new UserDAO();
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
	

	@Test
	public void testCreateUsersFieldsNotSet() {
		
		Assertions.assertThrows(PersistenceException.class, () -> {
			// create Users instance
			Users user = new Users();
			
			userDAO.create(user);
			
		});
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
	
	@Test
	public void testDeleteUsersNotPresent() {
		
		Assertions.assertThrows(EntityNotFoundException.class, ()->{
			Integer userId = 99;
			userDAO.delete(userId);
		});
	}
	
	@Test
	public void testListAllUsers() {
		List<Users> listUsers = userDAO.listAll();
		
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCountUsers() {
		long numberOfUsers = userDAO.count();
		
		assertTrue(numberOfUsers > 0);
	}
	
	@Test
	public void testFindByEmailUsers() {
		String email = "abc@example.com";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLoginUsersFound() {
		String email = "kenny@example";
		String password = "pass";
		
		boolean found = userDAO.checkLogin(email, password);
		
		assertTrue(found);	
	}
	
	@Test
	public void testCheckLoginUsersNotFound() {
		String email = "jay@example";
		String password = "pass123";
		
		boolean found = userDAO.checkLogin(email, password);
		
		assertFalse(found);	
	}

	@AfterAll
	public static void tearDown() {
		userDAO.close();
	}

}
