package com.fas.fcdsystem.mockito.argument_matchers;

import com.fas.fcdsystem.mockito.common.model.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository {
	void save(Book book);
	Book findBookById(String bookId);
	Book findBookByTitleAndPublishedDate(String title, LocalDate localDate);
	Book findBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital);
	void saveAll(List<Book> books);
}
