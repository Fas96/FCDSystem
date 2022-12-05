package com.fas.fcdsystem.mockito.argument_captor;

import com.fas.fcdsystem.mockito.common.model.Book;

public interface BookRepository {
	void save(Book book);
	Book findBookById(String bookId);
}
