package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

}
