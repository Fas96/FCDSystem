package com.fas.fcdsystem.mockito.bdd.behavior_verification;

import com.fas.fcdsystem.mockito.common.model.Book;

public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public void updatePrice(String bookId, int updatedPrice){
		Book book = bookRepository.findBookById(bookId);
		book.setPrice(updatedPrice);
		bookRepository.save(book);
	}
}
