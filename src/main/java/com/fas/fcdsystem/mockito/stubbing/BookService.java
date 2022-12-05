package com.fas.fcdsystem.mockito.stubbing;

import java.util.List;

public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> getNewBooksWithAppliedDiscount(int discountRate, int days){
		List<Book> newBooks = bookRepository.findNewBooks(days);

		for(Book book : newBooks){
			int price = book.getPrice();
			int newPrice = price - (discountRate * price / 100);
			book.setPrice(newPrice);
		}
		
		return newBooks;
	}
	
	public int calculateTotalCost(List<String> bookIds) {
		int total = 0;

		for(String bookId : bookIds){
			Book book = bookRepository.findBookByBookId(bookId);
			total +=  book.getPrice();
		}
		return total;
	}
	
	public void addBook(Book book) {
		bookRepository.save(book);
	}
	
	public void addBook(BookRequest bookRequest) {
		if(bookRequest.getPrice() <= 500){
			return;
		}
		Book book = new Book();
		book.setTitle(bookRequest.getTitle());
		book.setPrice(bookRequest.getPrice());
		book.setPublishedDate(bookRequest.getPublishedDate());
		bookRepository.save(book);
	}
}
