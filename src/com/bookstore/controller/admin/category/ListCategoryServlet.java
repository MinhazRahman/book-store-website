package com.bookstore.controller.admin.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CategoryServices;

/*When we click on the Categories link on the home page,
 * ListCategoryServlet is called which in turn calls listAll() 
 * method of CategoryServices class
 * 
 * */
@WebServlet("/admin/list_category")
public class ListCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListCategoryServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("List Categories");
		CategoryServices categoryServices = new CategoryServices(request, response);
		categoryServices.listCategory();
	}

}
