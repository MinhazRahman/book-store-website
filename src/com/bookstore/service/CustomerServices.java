package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;

public class CustomerServices extends BaseServices{
	private CustomerDAO customerDAO;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		
		customerDAO = new CustomerDAO();
	}
	
	
	// retrieves all the customers from the database, sets the request attributes
	// and forward the request and response to a server resource
	public void listCustomers(String message) throws ServletException, IOException {
		// get list of customers 
		List<Customer> listCustomers = customerDAO.listAll();
		
		// set list of customers as attribute
		request.setAttribute("listCustomers", listCustomers);
		
		// set message as attribute
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		// forward the request and response to customer_list.jsp page
		String listPage = "customer_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}
	
	// calls the listBooks(String) method
	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}

}
