package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * AdminLogoutServlet is called when admin user clicks on Logout button
 * on admin home page. 
 * doGet method forwards the request and response to login.jsp page
 */
@WebServlet("/admin/logout")
public class AdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get HttpSession object and remove the `userEmail` attribute
		HttpSession session = request.getSession();
		session.removeAttribute("userEmail");
		
		String message = "User logged Out sucessfully!";
		request.setAttribute("message", message);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(request, response);
	}

}
