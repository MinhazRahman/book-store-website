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

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;


public class CategoryServices extends BaseServices{
	private CategoryDAO categoryDAO;

	public CategoryServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		super(entityManager, request, response);
		categoryDAO = new CategoryDAO(entityManager);
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}
	
	public void listCategory(String message) throws ServletException, IOException {
		// get the list of all the categories
		List<Category> listCategory = categoryDAO.listAll();
		
		// set attributes to the request
		request.setAttribute("listCategory", listCategory);
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		// create request dispatcher
		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		
		// forward the request to the source
		requestDispatcher.forward(request, response);
	}

}
