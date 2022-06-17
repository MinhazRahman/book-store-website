package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices extends BaseServices{
	private UserDAO userDAO;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		userDAO = new UserDAO();
	}

	public void listUser() throws ServletException, IOException {
		listUser(null);
	}

	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();

		request.setAttribute("listUsers", listUsers);

		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");

		Users user = userDAO.findByEmail(email);

		if (user != null) {
			String message = "Could not create a user. A user with email " + email + " already exists!";

			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Users newUser = new Users(email, fullName, password);
			userDAO.create(newUser);
			listUser("New User Created Successfully!");
		}

	}

	public void editUser() throws ServletException, IOException {
		// get id from the request URL
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);

		String editPage = "user_form.jsp";
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		// retrieve user information from the request
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");

		// if there is already another user with the same email address
		// then don't update the user and forward the request to message.jsp page
		// and show the error message
		Users userById = userDAO.get(userId);
		Users userByEmail = userDAO.findByEmail(email);
		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			
			String message = "Could not update! user with " + email + " already exists!";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			
			// create the user that will be updated
			Users userToBeUpdated = new Users(userId, email, fullName, password);
			userDAO.update(userToBeUpdated);

			String message = "User has been updated successfully!";
			listUser(message);
			
		}
	}
	
	public void deleteUser() throws ServletException, IOException {
		// retrieve the user id from the request
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		// delete the user by id
		userDAO.delete(userId);
		
		//call the listUser() method to refresh the page
		String message = "User has been deleted successfully!";
		listUser(message);
	}
	
	public void login() throws ServletException, IOException {
		String message = null;
		// get parameters from the request object
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// check if the user with the given email and password 
		// is present or not
		boolean userFound = userDAO.checkLogin(email, password);
		
		if(userFound) {
			// store user email in an HttpSession object
			HttpSession session = request.getSession();
			session.setAttribute("userEmail", email);
			
			// forward request and response to AdminHomeServlet
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/");
			requestDispatcher.forward(request, response);
		}else {
			message = "Invalid login! Please try again.";
			
			// add message attribute to the request
			request.setAttribute("message", message);
			
			// forward request and response to login.jsp page
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}

}














