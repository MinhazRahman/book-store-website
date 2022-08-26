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
	
	public void createCustomer() throws ServletException, IOException {
		// retrieve the value of the email field from the HttpServletRequest 
		String email = request.getParameter("email");
		Customer customer = customerDAO.findByEmail(email);
		String message = null;
		
		if(customer != null) {
			message = "Email address already exists.";
			listCustomers(message);
		}else {
			
			// create a Customer object and set all the properties of the object
			Customer newCustomer = new Customer();
			setFieldValuesOf(newCustomer);
			
			// create and save the new Customer in the DB
			customerDAO.create(newCustomer);
			
			message = "New customer created successfully";
			listCustomers(message);
		}
	}

	public void editCustomer() throws ServletException, IOException {
		// get the customer id from the request parameter and parse it to Integer
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		
		// get the Customer by id
		Customer customer = customerDAO.get(customerId);
		
		// set attribute to the network request
		request.setAttribute("customer", customer);
		
		// forward the request to the customer_form.jsp page
		String editPage = "customer_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCustomer() throws ServletException, IOException {
		String message;
		
		// get the customer id from the request parameter and parse it to Integer
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		// get email of the customer from the request parameter
		String email = request.getParameter("email");
				
		// retrieve the Customer by email
		Customer customer = customerDAO.findByEmail(email);
		
		if(customer != null && customer.getCustomerId() != customerId) {
			message = "Couldn't update the customer info, Please use a different email address.";
		}
		else {
			
			// retrieve the Customer by id and set all the properties of the Customer
			Customer customerToBeUpdated = customerDAO.get(customerId);
			setFieldValuesOf(customerToBeUpdated);
			
			// save the updated info
			customerDAO.update(customerToBeUpdated);
			
			message = "Customer updated successfully.";
		}
		
		// show the list of all the customers
		listCustomers(message);
		
	}

	public void registerCustomer() throws ServletException, IOException {
		// retrieve the value of the email field from the HttpServletRequest 
		String email = request.getParameter("email");
		Customer customer = customerDAO.findByEmail(email);
		String message = null;
		
		if(customer != null) {
			message = "Customer Registration failed. Email address already exists!";
		}else {
			
			// create a Customer object and set all the properties of the object
			Customer newCustomer = new Customer();
			setFieldValuesOf(newCustomer);
			
			// create and save the new Customer in the DB
			customerDAO.create(newCustomer);
			
			message = "Thank you for the registration!<br/>" + "<a href='login'>Click here</a> to login";
			
		}
		
		// set request attribute
		request.setAttribute("message", message);
		
		// forward the request to the message.jsp page
		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		requestDispatcher.forward(request, response);
	}

	private void setFieldValuesOf(Customer customer) {
		// retrieve all the field values from the HttpServletRequest
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");
		
		// set all the properties for the Customer
		if(email != null && !email.equals("")) {
			customer.setEmail(email);
		}
		
		customer.setFullname(fullName);
		
		if(password != null && !password.equals("")) {
			customer.setPassword(password);
		}
		
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipcode(zipCode);
		customer.setCountry(country);
	}
	
	public void deleteCustomer() throws ServletException, IOException {
		// get the customer id from the request parameter and parse it to Integer
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		// delete the customer by id
		customerDAO.delete(customerId);
		
		String message = "Deleted the customer successfully.";
		listCustomers(message);
	}

	public void displayLoginPage() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
		requestDispatcher.forward(request, response);
	}

	public void loginCustomer() throws ServletException, IOException {
		// retrieve the parameters from the HttpServletRequest object request
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		String message = null;
		
		if(customer == null) {
			message = "Invalid email address or password!";
			request.setAttribute("message", message);
			displayLoginPage();
		}else {
			// set session attribute
			request.getSession().setAttribute("loggedInCustomer", customer);
			// when the customer is logged-in successfully, then show customer profile
			showCustomerProfile();
		}
		
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		String customerProfilePage = "frontend/customer_profile.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(customerProfilePage);
		requestDispatcher.forward(request, response);
	}

	public void showEditCustomerProfileForm() throws ServletException, IOException {
		String customerProfilePage = "frontend/edit_customer_profile_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(customerProfilePage);
		requestDispatcher.forward(request, response);	
	}

	public void updateCustomerProfile() throws ServletException, IOException {

		Customer customer = (Customer) request.getSession().getAttribute("loggedInCustomer");
		setFieldValuesOf(customer);
		customerDAO.update(customer);
		
		showCustomerProfile();
	}
	


}
