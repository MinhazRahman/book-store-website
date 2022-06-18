package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices extends BaseServices {
	private CategoryDAO categoryDAO;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		categoryDAO = new CategoryDAO();
	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException {
		// get the list of all the categories
		List<Category> listCategory = categoryDAO.listAll();

		// set attributes to the request
		request.setAttribute("listCategory", listCategory);
		if (message != null) {
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
		if (category != null) {
			String message = "Couldn't create a category! A category with name " + categoryName + " already exists!!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			// Create the category
			Category newCategory = new Category(categoryName);
			categoryDAO.create(newCategory);
			// show the list of all categories
			listCategory("New Category has been created successfully!!");
		}
	}

	public void editCategory() throws ServletException, IOException {
		// get the parameters from the request
		int categoryId = Integer.parseInt(request.getParameter("id"));

		// find the category using the id
		Category category = categoryDAO.get(categoryId);

		// set the category object as the attribute of request object
		request.setAttribute("category", category);

		// forward the request to the edit page (category_form.jsp file)
		String editPage = "category_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		// get the parameters from the request
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");

		// find the category by name
		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);

		// if the category already exists in the database then
		// forward the request to the message.jsp file
		if (categoryByName != null && categoryByName.getCategoryId() != categoryById.getCategoryId()) {
			String message = "Couldn't create a category! A category with name " + categoryName + " already exists!!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			// if there is no category in the database then
			// update the category and show the category list
			Category categoryToBeUpdated = new Category(categoryId, categoryName);
			categoryDAO.update(categoryToBeUpdated);
			listCategory("Category has been updated successfully!!");	
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		// get parameters from request object
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		BookDAO bookDAO = new BookDAO();
		long numberOfBooks = bookDAO.countByCategory(categoryId);
		String message;
		
		// find the category by id in the database and 
		// if the category is not found then show the error message
		Category category = categoryDAO.get(categoryId);
		if(category == null) {
			message = "Couldn't find the category with id " + categoryId + 
								", or it might have been deleted!!";
			request.setAttribute("message", message);
			// get RequestDispatcher object and 
			// forward the request and response to message.jsp file
			String resource = "message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(resource);
			requestDispatcher.forward(request, response);
			return;	
		}else if(numberOfBooks > 0){
			message = "Couldn't delete the category!";
			
		}else {
			
			// delete category from the database
			categoryDAO.delete(categoryId);
			
			// show the updated list of categories
			message = "Category has been deleted successfully!!";
		}
		
		listCategory(message);
	}

}
