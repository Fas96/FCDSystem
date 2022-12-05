package com.fas.fcdsystem.mockito.bdd.behavior_verification;

import com.fas.fcdsystem.mockito.common.model.Book;

public interface BookRepository {
	void save(Book book);
	Book findBookById(String bookId);
}
