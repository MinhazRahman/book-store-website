package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

// CommonFilter class intercepts all the requests that come from the 
// application front-end
@WebFilter("/*")
public class CommonFilter implements Filter {
	
	private final CategoryDAO categoryDAO;

  
    public CommonFilter() {
    	categoryDAO = new CategoryDAO();
    }

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		// filters only those request that come from application front-end
		if(!path.startsWith("/admin/")) {
			// retrieve the list of category
			List<Category> listCategory = categoryDAO.listAll();
			request.setAttribute("listCategory", listCategory);
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
