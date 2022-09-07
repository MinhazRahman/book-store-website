package com.bookstore.entity;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class BookRatingTest {
	
	@Test
	public void testAverageRatingOneReview() {
		Book book = new Book();
		
		// create a set object that contains set of reviews
		Set<Review> setOfReviews = new HashSet<>();
		
		// create review object
		Review review = new Review();
		review.setRating(5);
		
		// add reviews to the set
		setOfReviews.add(review);
		
		// set reviews for the book
		book.setReviews(setOfReviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(5.0, averageRating, 0.0);
	}
	
	@Test
	public void testAverageRatingNoReviews() {
		Book book = new Book();
		
		
		float averageRating = book.getAverageRating();
		
		assertEquals(0.0, averageRating, 0.0);
	}
	
	@Test
	public void testAverageRatingMultipleReviews() {
		Book book = new Book();
		
		// create a set object that contains set of reviews
		Set<Review> setOfReviews = new HashSet<>();
		
		// create review objects
		Review review1 = new Review();
		review1.setRating(5);
		
		Review review2 = new Review();
		review2.setRating(4);
				
		Review review3 = new Review();
		review3.setRating(3);
		
		// add reviews to the set
		setOfReviews.add(review1);
		setOfReviews.add(review2);
		setOfReviews.add(review3);
		
		// set reviews for the book
		book.setReviews(setOfReviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(4.0, averageRating, 0.0);
	}

	@Test
	public void testRatingStringAllOff() {
		float averageRating = 0.0f;
		Book book = new Book();
		String actual = book.getRatingString(averageRating);
		
		String expected = "off,off,off,off,off";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingStringAllOn() {
		float averageRating = 5.0f;
		Book book = new Book();
		String actual = book.getRatingString(averageRating);
		
		String expected = "on,on,on,on,on";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingStringOnHalf() {
		float averageRating = 4.5f;
		Book book = new Book();
		String actual = book.getRatingString(averageRating);
		
		String expected = "on,on,on,on,half";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingStringOnHalfOff() {
		float averageRating = 3.6f;
		Book book = new Book();
		String actual = book.getRatingString(averageRating);
		
		String expected = "on,on,on,half,off";
		
		assertEquals(expected, actual);
	}
}
