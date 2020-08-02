package com.bookstore.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

/* JUnit 5 or JUnit Jupiter test case, the annotations are different from JUnit 4  */
class BookDAOTest extends BaseDAOTest{
	
	private static BookDAO bookDAO;

	@BeforeAll
	public static void setUp() {
		BaseDAOTest.setUp();
		// initialize the BookDAO object
		bookDAO = new BookDAO(entityManager);
	}

	@Test
	void testCreateBook() throws ParseException, IOException {
		// create a Book object
		Book newBook = new Book();
		
		// for a book we need to have a category
		// create a category by category_id and name
		Category newCategory = new Category(32, "Programming");
		
		// set attributes
		newBook.setCategory(newCategory);
		newBook.setTitle("Python in Action"); // title has to be unique
		newBook.setAuthor("Jhon Doe");
		newBook.setDescription("How to use Java 8's powerful new features");
		newBook.setPrice(36.72f);
		newBook.setIsbn("1617291994");
		
		// set publish date
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2008");
		newBook.setPublishDate(publishDate);
		
		// convert the image into byte array and set as the property of the book object
		String imagePath = "/Users/mohammed/Workspace/BookStoreProject/BookStoreWebsite/WebContent/images/books/Java8InAction.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		// create books
		Book createdBook = bookDAO.create(newBook);
		
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		// create a Book object
		Book bookToBeUpdated = new Book();
		bookToBeUpdated.setBookId(32);
		
		// for a book we need to have a category
		// create a category by category_id and name
		Category newCategory = new Category(32, "Programming");
		
		// set attributes
		bookToBeUpdated.setCategory(newCategory);
		bookToBeUpdated.setTitle("Effective Java (3rd Edition)"); // title has to be unique
		bookToBeUpdated.setAuthor("Joshua Bloch");
		bookToBeUpdated.setDescription("How to use Java effectively");
		bookToBeUpdated.setPrice(36.72f);
		bookToBeUpdated.setIsbn("1617291995");
		
		// set publish date
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("09/28/2014");
		bookToBeUpdated.setPublishDate(publishDate);
		
		// convert the image into byte array and set as the property of the book object
		String imagePath = "/Users/mohammed/Workspace/BookStoreProject/BookStoreWebsite/WebContent/images/books/EffectiveJava.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		bookToBeUpdated.setImage(imageBytes);
		
		// create books
		Book updatedBook = bookDAO.update(bookToBeUpdated);
		
		assertEquals(updatedBook.getTitle(), "Effective Java (3rd Edition)");
		
	}
	
	@Test
	public void testGetBookFound() {
		// create an instance of Book
		Integer bookId = 34;
		
		Book book = bookDAO.get(bookId);
		
		String expectedTitle = "Java 8 in Action";
		String actualTitle = book.getTitle();
		
		assertNotNull(book);
		assertEquals(actualTitle, expectedTitle);
	}
	
	@Test
	public void testGetBookNotFound() {
		// create an instance of Book
		Integer bookId = 100;
		
		Book book = bookDAO.get(bookId);
		
		assertNull(book);
	}
	
	@Test
	public void testDeleteBook() {
		Integer bookId = 32;
		
		bookDAO.delete(bookId);
		
		Book book = bookDAO.get(bookId);
		
		assertNull(book);
	}
	
	@Test 
	public void testListAllBook() {
		List<Book> listBook = bookDAO.listAll();
		
		listBook.forEach(b -> System.out.println(b.getTitle() + " --> " + b.getAuthor()));
		
		assertFalse(listBook.isEmpty());
		assertTrue(listBook.size() > 0);
	}
	
	@Test
	public void testFindByTitleBookFound() {
		Book book = bookDAO.findByTitle("Python in Action");
		
		assertNotNull(book);
		assertEquals(book.getTitle(), "Python in Action");
	}
	
	@Test
	public void testFindByTitleBookNotFound() {
		Book book = bookDAO.findByTitle("Java Complete Reference");
		
		assertNull(book);
	}
	
	@AfterAll
	public static void tearDown() {
		BaseDAOTest.setUp();
	}

}
