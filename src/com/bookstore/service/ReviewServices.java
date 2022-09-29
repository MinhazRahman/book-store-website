package com.bookstore.service;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
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

	public void updateReview() throws ServletException, IOException {
		// retrieve the parameters from the network request
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		// retrieve the Review by id from the DB
		Review reviewToBeUpdated = reviewDAO.get(reviewId);
		
		// set the updated values
		reviewToBeUpdated.setHeadline(headline);
		reviewToBeUpdated.setComment(comment);
		
		// save the Review object into the DB and show the list of all reviews
		reviewDAO.update(reviewToBeUpdated);
		
		String message = "Review updated successfully!";
		listAllReviews(message);
	}

	public void deleteReview() throws ServletException, IOException {
		// retrieve the review id from the request parameter
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		
		// delete the Review by id 
		reviewDAO.delete(reviewId);
		
		// show the updated list of reviews
		String message = "Deleted the review successfully!";
		listAllReviews(message);
	}

	public void showWriteReviewForm() throws ServletException, IOException {
		// retrieve the id of the book from the request parameter
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		// get the Book by id from DB 
		BookDAO bookDAO  = new BookDAO();
		Book book = bookDAO.get(bookId);
		
		// Get the HttpSession object from the network request
		HttpSession httpSession = request.getSession();
		
		// retrieve the Customer from the session object
		Customer customer = (Customer) httpSession.getAttribute("loggedInCustomer");
		
		// set Book object in the HttpSession object
		httpSession.setAttribute("book", book);
		
		// Check whether the Customer already has written a review for the book
		Review review = reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);
		
		String targetPage = "frontend/write_review_form.jsp";
		
		if(review != null) {
			request.setAttribute("review", review);
			targetPage = "frontend/review_info.jsp";
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetPage);
		requestDispatcher.forward(request, response);
		
	}

	public void submitReview() throws ServletException, IOException {
		// retrieve the parameters from the network request
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		// get the Book by id from DB 
		BookDAO bookDAO  = new BookDAO();
		Book book = bookDAO.get(bookId);
		
		// retrieve the Customer from the session object
		Customer customer = (Customer) request.getSession().getAttribute("loggedInCustomer");
		
		// create Review object and set properties
		Review reviewToBeCreated = new Review();
		
		reviewToBeCreated.setBook(book);
		reviewToBeCreated.setCustomer(customer);
		reviewToBeCreated.setRating(rating);
		reviewToBeCreated.setHeadline(headline);
		reviewToBeCreated.setComment(comment);
		
		// create the Review object
		reviewDAO.create(reviewToBeCreated);
		
		// forward the request to message page
		String messagePage = "frontend/review_submission_result.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		requestDispatcher.forward(request, response);
	}

}
