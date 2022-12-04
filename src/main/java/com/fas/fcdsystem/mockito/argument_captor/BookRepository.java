package com.fas.fcdsystem.mockito.argument_captor;

public interface BookRepository {
	void save(Book book);
	Book findBookById(String bookId);
}
