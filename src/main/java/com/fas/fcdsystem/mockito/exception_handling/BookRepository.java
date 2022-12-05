package com.fas.fcdsystem.mockito.exception_handling;

import com.fas.fcdsystem.mockito.common.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository {
	List<Book> findAllBooks() throws SQLException;
	void save(Book book) throws SQLException;
}
