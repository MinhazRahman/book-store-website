package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Review;

public class ReviewServices extends BaseServices{
	
	private ReviewDAO reviewDAO;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		
		reviewDAO = new ReviewDAO();
	}
	
	// retrieves all the reviews from the database, sets the request attributes
	// and forward the request and response to a server resource
	public void listAllReviews(String message) throws ServletException, IOException {
		// get the list of reviews 
		List<Review> listReviews = reviewDAO.listAll();
		
		// set list of reviews as attribute
		request.setAttribute("listReviews", listReviews);
		
		// set message as attribute if message is not null
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		// forward the request and response to review_list.jsp page
		String listReviewPage = "review_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listReviewPage);
		requestDispatcher.forward(request, response);
		
		
	}
	
	// calls the listReviews(String) method
	public void listAllReviews() throws ServletException, IOException {
		listAllReviews(null);
	}

	public void showEditReviewForm() throws ServletException, IOException {
		// retrieve the review id from the network request
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		
		// retrieve the Review from the DB
		Review reviewToBeUpdated = reviewDAO.get(reviewId);
		
		// set the request attribute
		request.setAttribute("reviewToBeUpdated", reviewToBeUpdated);
		
		String editReviewPage = "edit_review_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editReviewPage);
		requestDispatcher.forward(request, response);
	}

}
