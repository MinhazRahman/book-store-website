package com.bookstore.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest {

	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUp() {
		BaseDAOTest.setUp();
		// create a user into the database
		categoryDAO = new CategoryDAO(entityManager);
	}

	@Test
	public void testCreateCategory() {
		// create an instance
		Category newCategory = new Category("Lifestyle");

		Category category = categoryDAO.create(newCategory);

		assertNotNull(category);
		assertTrue(category.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		// create an instance
		Category categoryToBeUpdated = new Category("Lifestyle");
		categoryToBeUpdated.setCategoryId(11);

		Category updatedCategory = categoryDAO.update(categoryToBeUpdated);
		
		String expectedCategoryName = categoryToBeUpdated.getName();
		String actualCategoryName = updatedCategory.getName();

		assertNotNull(updatedCategory);
		assertEquals(expectedCategoryName, actualCategoryName);
	}
	
	@Test
	public void testGetCategory() {
		int categoryId = 20;
		
		Category category = categoryDAO.get(categoryId);
		
		assertNotNull(category);
		assertEquals(categoryId, category.getCategoryId());
	}
	
	@Test
	public void testGetCategoryNotFound() {
		int categoryId = 99;
		
		Category category = categoryDAO.get(categoryId);
		
		assertNull(category);
		
	}
	
	@Test
	public void testDeleteCategory() {
		int categoryId = 11;
		categoryDAO.delete(categoryId);
		
		Category category = categoryDAO.get(categoryId);
		assertNull(category);
	}

	@AfterClass
	public static void tearDown() {
		BaseDAOTest.setUp();
	}
}
