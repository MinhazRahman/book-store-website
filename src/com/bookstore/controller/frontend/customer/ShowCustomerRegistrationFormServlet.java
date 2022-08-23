package com.bookstore.controller.frontend.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ShowCustomerRegistrationFormServlet forwards the network request to customer_registration_form.jsp page.
 * One of the reasons we are creating this servlet is to hide the frontend directory from the end-users.
 * 
 * */
@WebServlet("/register")
public class ShowCustomerRegistrationFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowCustomerRegistrationFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String registrationFrom = "frontend/customer_registration_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(registrationFrom);
		requestDispatcher.forward(request, response);
	}

}
