package com.bookstore.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServices {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public BaseServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

}
