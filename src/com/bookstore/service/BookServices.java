package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices extends BaseServices{
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;

	public BookServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		super(entityManager, request, response);
		bookDAO = new BookDAO(entityManager);
		categoryDAO = new CategoryDAO(entityManager);
	}
	
	// retrieves all the books from the database, sets the request attributes
	// and forward the request and response to a server resource
	public void listBooks(String message) throws ServletException, IOException {
		// get list of books 
		List<Book> listBooks = bookDAO.listAll();
		
		// set list of books as attribute
		request.setAttribute("listBooks", listBooks);
		
		// set message as attribute
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		// forward the request and response to book_list.jsp page
		String listPage = "book_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	// calls the listBooks(String) method
	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}
	
	public void showNewBookForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		
		// set list of category as attribute
		request.setAttribute("listCategory", listCategory);
		
		// forward the request and response to book_form.jsp page
		String bookFormPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(bookFormPage);
		requestDispatcher.forward(request, response);
	}

	public void createBook() throws ServletException, IOException{
		// create a new book instance
		Book newBook = new Book();
		
		// retrieve all the data from the request
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		
		// if there is another book with the same title in the database
		// don't create the new book
		Book book = bookDAO.findByTitle(title);
		if(book != null) {
			String message = "Could not create a book. Title " + "`"  + title +"`" + " already exists!";
			listBooks(message);
			return;
		}
		
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		Float price = Float.parseFloat(request.getParameter("price"));
		String description = request.getParameter("description");
		
		// retrieve publish date as string and convert it to Date
		String publishDateString = request.getParameter("publishDate");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(publishDateString);
		} catch (ParseException e) {
			
			e.printStackTrace();
			throw new ServletException("Error parsing publish date, format: MM/dd/yyyy");
		}
		
		// set the properties of the book
		
		// retrieve the category from database by category id
		Category category = categoryDAO.get(categoryId);
		newBook.setCategory(category);
		
		newBook.setTitle(title);
		newBook.setAuthor(author);
		newBook.setIsbn(isbn);
		newBook.setPrice(price);
		newBook.setDescription(description);
		newBook.setPublishDate(publishDate);
		
		// retrieve image data from multipart request and convert it to an array of bytes
		Part part = request.getPart("bookImage");
		byte[] imageBytes = null;
		
		// read image data as an array of bytes
		if(part != null && part.getSize() > 0) {
			long size = part.getSize();
			imageBytes = new byte[(int) size];
			
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			newBook.setImage(imageBytes);
		}
		
		// create the book
		Book createdBook = bookDAO.create(newBook);
	
		if(createdBook.getBookId() > 0) {
			String message = "A new book has been created succefully.";
			listBooks(message);
		}
	}

	public void editBook() throws ServletException, IOException {
		// retrieve data from the request
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		
		// find a book by id
		Book book = bookDAO.get(bookId);
		
		// retrieve a list of categories from the database
		List<Category> listCategory = categoryDAO.listAll();
		
		// set list of category as attribute
		request.setAttribute("listCategory", listCategory);
		
		// set the book object as the attribute of the request
		request.setAttribute("book", book);
		
		// forward the request and response to book_form.jsp file
		String bookFormPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(bookFormPage);
		requestDispatcher.forward(request, response);
		
	}
	
	public void updateBook() throws ServletException, IOException {
		String message = null;
		
		// retrieve data from the request object
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		// find the book in the database by id
		Book bookToBeUpdated = bookDAO.get(bookId);
		
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryDAO.get(categoryId);
		
		String title = request.getParameter("title");
		// check if there is anotherBook other than the bookToBeUpdated 
		// with the same title already exists in the database, then don't update the book
		Book anotherBook = bookDAO.findByTitle(title);
		if(anotherBook != null && bookToBeUpdated.getBookId() != anotherBook.getBookId()) {
			message = "Could not update the book! There is another book with the title " + "`" + 
							title + "`" + " already exists!";
			listBooks(message);
			return;
		}
		
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		Float price = Float.parseFloat(request.getParameter("price"));
		String description = request.getParameter("description");
		
		// retrieve publish date as a string and convert it to Date
		String publishDateString = request.getParameter("publishDate");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(publishDateString);
		} catch (ParseException e) {
			
			e.printStackTrace();
			throw new ServletException("Error parsing publish date, format: MM/dd/yyyy");
		}
		
		// set the properties of the book

		bookToBeUpdated.setCategory(category);

		bookToBeUpdated.setTitle(title);
		bookToBeUpdated.setAuthor(author);
		bookToBeUpdated.setIsbn(isbn);
		bookToBeUpdated.setPrice(price);
		bookToBeUpdated.setDescription(description);
		bookToBeUpdated.setPublishDate(publishDate);

		// retrieve image data from multipart request and convert it to an array of bytes
		Part part = request.getPart("bookImage");
		byte[] imageBytes = null;

		// read image data as an array of bytes
		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			imageBytes = new byte[(int) size];

			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();

			bookToBeUpdated.setImage(imageBytes);
		}
		
		// update the book
		Book updatedBook = bookDAO.update(bookToBeUpdated);
		if(updatedBook.getBookId() > 0) {
			message = "Book has been updated successfully!";
			listBooks(message);
		}
	}

}
