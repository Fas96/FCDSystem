package com.fas.fcdsystem.mockito.test_doubles.dummy;
import com.fas.fcdsystem.mockito.common.model.Book;
public class BookService {
	
	private BookRepository bookRepository;
	private EmailService emailService;
	
	public BookService(BookRepository bookRepository, EmailService emailService) {
		this.bookRepository = bookRepository;
		this.emailService = emailService;
	}
	
	public void addBook(Book book){
		bookRepository.save(book);
	}
	
	public int findNumberOfBooks(){
		return bookRepository.findAll().size();
	}
	
	// Other methods which use EmailService
}
