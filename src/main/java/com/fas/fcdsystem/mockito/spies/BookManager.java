package com.fas.fcdsystem.mockito.spies;

import com.fas.fcdsystem.mockito.common.model.Book;
public class BookManager {
	
	private BookService bookService;
	
	public BookManager(BookService bookService) {
		this.bookService = bookService;
	}
	
	public int applyDiscountOnBook(String bookId, int discountRate) {
		Book book = bookService.findBook(bookId); // We need to Mock
		int discountedPrice = bookService.getAppliedDiscount(book, discountRate);
		return discountedPrice;
	}
}
