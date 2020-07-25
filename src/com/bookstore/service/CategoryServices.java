package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
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
		
		// forward the request to the category_list.jsp file
		requestDispatcher.forward(request, response);
	}
	
	public void createCategory() throws ServletException, IOException {
		
		// get the parameters from the request object
		String categoryName = request.getParameter("name");
		
		// Check if the Category name already exists in the database.
		// if the category name is present, then don't create the category
		Category category = categoryDAO.findByName(categoryName);
		if(category != null) {
			String message = "Couldn't create a category! A category with name " + categoryName +
								" already exists!!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			// Create the category 
			Category newCategory = new Category(categoryName);
			categoryDAO.create(newCategory);
			// show the list of all categories
			listCategory("New Category has been created successfully!!");
		}
	}

}
