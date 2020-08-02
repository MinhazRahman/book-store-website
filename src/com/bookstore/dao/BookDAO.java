package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book>{

	public BookDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public Book create(Book book) {
		return null;
	}
	
	@Override
	public Book update(Book book) {
		return null;
	}

	@Override
	public Book get(Object id) {
		
		return null;
	}

	@Override
	public void delete(Object id) {
		
		
	}

	@Override
	public List<Book> listAll() {
		
		return null;
	}

	@Override
	public long count() {
		
		return 0;
	}

}
