package com.bookstore.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest{
	
	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUp() {
		BaseDAOTest.setUp();
		// create a user into the database
		categoryDAO = new CategoryDAO(entityManager);
	}
	
	@Test
	public void testCreateCategory() {
		//create an instance
		Category newCategory = new Category("Lifestyle");
		
		Category category = categoryDAO.create(newCategory);
		
		assertNotNull(category);
		assertTrue(category.getCategoryId() > 0);	
	}
	
	@AfterClass
	public static void tearDown() {
		BaseDAOTest.setUp();
	}
}
