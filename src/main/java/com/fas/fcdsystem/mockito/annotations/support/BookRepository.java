package com.fas.fcdsystem.mockito.annotations.support;

import com.fas.fcdsystem.mockito.common.model.Book;

import java.util.List;

public interface BookRepository {
	List<Book> findNewBooks(int days);
}
