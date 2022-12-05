package com.fas.fcdsystem.mockito.test_doubles.fake;
import com.fas.fcdsystem.mockito.common.model.Book;
import java.util.Collection;

public interface BookRepository {
	void save(Book book);
	Collection<Book> findAll();
}
