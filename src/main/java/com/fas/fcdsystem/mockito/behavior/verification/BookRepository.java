package com.fas.fcdsystem.mockito.behavior.verification;

import com.fas.fcdsystem.mockito.common.model.Book;

import java.util.List;

public interface BookRepository {
	void save(Book book);
	Book findBookById(String bookId);
}
