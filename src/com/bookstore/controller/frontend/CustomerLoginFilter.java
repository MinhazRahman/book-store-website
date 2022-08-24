package com.bookstore.controller.frontend;

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
 * CustomerLoginFilter is used to prevent unauthorized access to the customer account
 */
@WebFilter("/*")
public class CustomerLoginFilter implements Filter {

    public CustomerLoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// check whether the customer has logged in the website or not
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpRequest.getSession(false);
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		// if the requests come from the admin section then continue filtering
		if(path.startsWith("/admin/")) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
			return;
		}
		
		boolean loggedIn = httpSession != null && httpSession.getAttribute("loggedInCustomer") != null;
		
		// if the customer is not logged-in forward the request to the login.jsp page
		if(!loggedIn && path.startsWith("/view_profile")) {
			String loginPage = "frontend/login.jsp";
			RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher(loginPage);
			requestDispatcher.forward(request, response);
			
		}else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
