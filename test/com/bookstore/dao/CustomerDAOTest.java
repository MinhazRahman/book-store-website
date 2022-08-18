package com.bookstore.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Customer;

/* JUnit 5 or JUnit Jupiter test case, the annotations are different from JUnit 4  */
public class CustomerDAOTest {
	
	private static CustomerDAO customerDAO;
	
	@BeforeAll
	public static void setUp() {
		// initialize the CustomerDAO object
		customerDAO = new CustomerDAO();
	}
	
	@Test
	public void testGetCustomer() {
		Integer customerId = 13;
		Customer customer = customerDAO.get(customerId);
		
		assertNotNull(customer);
		
	}
	
	@Test
	public void testUpdateCustomer() {
		Integer customerId = 13;
		Customer customer = customerDAO.get(customerId);
		
		String fullName = "Jhony Doe";
		
		customer.setFullname(fullName);
		
		Customer updatedCustomer = customerDAO.update(customer);
		
		assertTrue(updatedCustomer.getFullname().equals(fullName));	
	}
	
	@Test
	public void testDeleteCustomer() {
		Integer customerId = 13;
		customerDAO.delete(customerId);
		
		Customer customer = customerDAO.get(customerId);
		
		assertNull(customer);	
	}
	
	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		
		customer.setEmail("jhony@example.com");
		customer.setFullname("Jhony mike");
		customer.setCity("Boston");
		customer.setCountry("United States");
		customer.setAddress("168 Gothic drive");
		customer.setPassword("456");
		customer.setPhone("+13478719890");
		customer.setZipcode("11432");
		
		Customer savedCustomer = customerDAO.create(customer);
		
		assertTrue(savedCustomer.getCustomerId() > 0);
		
	}
	
	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDAO.listAll();
		
		assertFalse(listCustomers.isEmpty());
	}
	
	@Test
	public void testCountCustomer() {
		long customers = customerDAO.count();
		
		assertTrue(customers == 2);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "doe1@example.com";
		Customer customer = customerDAO.findByEmail(email);
		
		assertNull(customer);
	}
	
	@AfterAll
	public static void tearDown() {
		customerDAO.close();
	}

}
