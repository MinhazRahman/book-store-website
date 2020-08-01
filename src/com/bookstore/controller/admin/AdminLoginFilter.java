package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * AdminLoginFilter filters the requests when trying to access the admin page.
 * Filtering conditions:
 * - user logging in for the first time
 * - user is already logged in
 * - user is already logged in and again requesting for login page
 * - 
 */
@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

    public AdminLoginFilter() {
       
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		// check if a user is already logged in or not
		boolean loggedIn = session !=null && session.getAttribute("userEmail") != null;
		
		// check if the current URI is equal to login URI
		String loginURI = httpRequest.getContextPath() + "/admin/login";
		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
		
		// check if the URI requested is for login page
		boolean loginPage = httpRequest.getRequestURI().endsWith("login.jsp");
		
		if(loggedIn && (loginRequest || loginPage)) {
			// forward the request and response to admin page
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/");
			requestDispatcher.forward(httpRequest, response);
		}
		else if(loggedIn || loginRequest) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}else {
			// forward the request and response to login.jsp file
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(httpRequest, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
