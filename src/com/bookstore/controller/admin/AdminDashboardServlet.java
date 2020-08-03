package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;

/**
 * AdminDashboardServlet is invoked when we click on the Dashboard link
 * on the admin home page
 */
@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDashboardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// forward request and response to AdminHomeServlet
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/");
		requestDispatcher.forward(request, response);
	}

}
