package com.bookstore.controller.admin.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.CategoryServices;

/**
 * CreateCategoryServlet is called when we click the Create button on
 * category_form.jsp page to create a Category with a given name.
 * 
 * CreateCategoryServlet in turn calls the createCategory() method of
 * CategoryServices class
 */
@WebServlet("/admin/create_category")
public class CreateCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public CreateCategoryServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CategoryServices userServices = new CategoryServices(entityManager, request, response);
		userServices.createCategory();
	}

}
