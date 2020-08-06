package com.bookstore.controller.admin.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.BookServices;

/**
 * CreateBookServlet is invoked when we click on the Save button
 * on the book_form.jsp page
 */
@WebServlet("/admin/create_book")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10,	// 10KB
		maxFileSize = 1024 * 300, 		// 300 KB
		maxRequestSize = 1024 * 1024	// 1 MB
)
public class CreateBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public CreateBookServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookServices = new BookServices(entityManager, request, response);
		bookServices.createBook();
	}

}