package com.fas.fcdsystem.mockito.test_doubles.stub;

import com.fas.fcdsystem.mockito.common.model.Book;

import java.util.List;

public interface BookRepository {
	List<Book> findNewBooks(int days);
}
