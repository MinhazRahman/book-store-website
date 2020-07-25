package com.bookstore.service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServices {
	protected EntityManager entityManager;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public BaseServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
	}

}
