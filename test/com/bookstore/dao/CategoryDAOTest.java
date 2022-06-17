package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.bookstore.entity.Category;

/* JUnit 4 test case */
public class CategoryDAOTest{

	private static CategoryDAO categoryDAO;

	@BeforeAll
	public static void setUp() {
		
		categoryDAO = new CategoryDAO();
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
	
	@Test
	public void testListAllCategory() {
		List<Category> listCategory = categoryDAO.listAll();
		
		listCategory.forEach(c -> System.out.println(c.getName()));
		assertTrue(listCategory.size() > 0);
	}
	
	@Test
	public void testCountCategory() {
		long numberOfCatetories = categoryDAO.count();
		
		System.out.println(numberOfCatetories);
		assertEquals(16, numberOfCatetories);
	}
	
	@Test 
	public void testFindByNameCategory() {
		String categoryName = "Health";
		
		Category category = categoryDAO.findByName(categoryName);
		
		assertNotNull(category);
	}
	
	@Test 
	public void testFindByNameCategoryNotFound() {
		String categoryName = "Computing";
		
		Category category = categoryDAO.findByName(categoryName);
		
		assertNull(category);
	}

	@AfterAll
	public static void tearDown() {
		categoryDAO.close();
	}
}
