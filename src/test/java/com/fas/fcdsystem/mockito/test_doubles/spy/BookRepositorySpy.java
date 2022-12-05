package com.fas.fcdsystem.mockito.test_doubles.spy;
import com.fas.fcdsystem.mockito.common.model.Book;
public class BookRepositorySpy implements BookRepository {
	
	int saveCalled = 0;
	Book lastAddedBook = null;
	
	@Override
	public void save(Book book) {
		saveCalled++;
		lastAddedBook = book;
	}
	
	public int timesCalled(){
		return saveCalled;
	}
	
	public boolean calledWith(Book book){
		return lastAddedBook.equals(book);
	}
	
}
