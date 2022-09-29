package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

/* JUnit 5 or JUnit Jupiter test case, the annotations are different from JUnit 4  */
public class ReviewDAOTest {
	
	private static ReviewDAO reviewDAO;
	
	@BeforeAll
	public static void setUp() {
		// initialize the ReviewDAO object
		reviewDAO = new ReviewDAO();
	}
	
	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book();
		
		book.setBookId(37);
		
		Customer customer = new Customer();
		customer.setCustomerId(22);
		
		review.setBook(book);
		review.setCustomer(customer);
		
		review.setHeadline("A good book!");
		review.setRating(4);
		review.setComment("Teaches you the basics of dsa.");
		
		Review savedReview = reviewDAO.create(review);
		
		assertTrue(savedReview.getReviewId() > 0);	
	}
	
	@Test
	public void testGetReviewSuccess() {
		Integer reviewId = 15; // when id is present in the DB
		Review review = reviewDAO.get(reviewId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testGetReviewFailure() {
		Integer reviewId = 20; // when id is not present in the DB
		Review review = reviewDAO.get(reviewId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testUpdateReview() {
		Integer reviewId = 15; // when id is present in the DB
		Review review = reviewDAO.get(reviewId);
		
		// update the headline
		review.setHeadline("Nice book!");
		
		// get the updated review
		Review updatedReview = reviewDAO.update(review);
		
		assertEquals(review.getHeadline(), updatedReview.getHeadline());
	}
	
	@Test
	public void testListAll() {
		List<Review> listReview = reviewDAO.listAll();
		
		for(Review review: listReview) {
			System.out.println(review.getHeadline() + ", " + review.getComment() + ", " + review.getCustomer().getFullname());
		}
		
		assertTrue(listReview.size() > 0);
	}
	
	@Test
	public void testCount() {
		long numberOfReviews = reviewDAO.count();
		assertTrue(numberOfReviews == 2);
	}
	
	@Test 
	public void testDelete() {
		Integer reviewId = 15;
		// delete a Review by id
		reviewDAO.delete(reviewId);
		
		// get the Review by id
		Review review = reviewDAO.get(reviewId);
		
		assertNull(review);
	}
	
	@Test
	public void testfindByCustomerAndBookNotFound() {
		Integer customerId = 99;
		Integer bookId = 99;
		
		Review review = reviewDAO.findByCustomerAndBook(customerId, bookId);
		
		assertNull(review);
	}
	
	@Test
	public void testfindByCustomerAndBookFound() {
		Integer customerId = 22;
		Integer bookId = 37;
		
		Review review = reviewDAO.findByCustomerAndBook(customerId, bookId);
		
		assertNotNull(review);
	}
	
	@AfterAll
	public static void tearDown() {
		reviewDAO.close();
	}

}
