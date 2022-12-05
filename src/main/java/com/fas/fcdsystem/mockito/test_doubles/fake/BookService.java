package com.fas.fcdsystem.mockito.test_doubles.fake;
import com.fas.fcdsystem.mockito.common.model.Book;
public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public void addBook(Book book){
		bookRepository.save(book);
	}
	
	public int findNumberOfBooks(){
		return bookRepository.findAll().size();
	}
}
