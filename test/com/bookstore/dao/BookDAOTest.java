package com.bookstore.dao;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		newBook.setTitle("Java 8 in Action"); // title has to be unique
		newBook.setAuthor("Raoul-Gabriel Urma");
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
		
		if(bookDAO == null) {
			System.out.println("bookDAO was not created");
		}
		
		// create books
		Book createdBook = bookDAO.create(newBook);
		
		if(createdBook == null) {
			System.out.println("Book was not created");
		}
		
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@AfterAll
	public static void tearDown() {
		BaseDAOTest.setUp();
	}

}
