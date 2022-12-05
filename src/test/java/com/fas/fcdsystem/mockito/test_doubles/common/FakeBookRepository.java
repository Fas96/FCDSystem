package com.fas.fcdsystem.mockito.test_doubles.common;
import com.fas.fcdsystem.mockito.common.model.Book;
import com.fas.fcdsystem.mockito.test_doubles.dummy.BookRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.fas.fcdsystem.mockito.common.model.Book;
public class FakeBookRepository implements BookRepository {
	
	// In memory database, HashMap or List
	Map<String, Book> bookStore = new HashMap<>();
	
	@Override
	public void save(Book book) {
		bookStore.put(book.getBookId(), book);
	}
	
	@Override
	public Collection<Book> findAll() {
		return bookStore.values();
	}
}
