package com.fas.fcdsystem.mockito.stubbing;
import com.fas.fcdsystem.mockito.common.model.Book;
import java.util.List;

public interface BookRepository {
	List<Book> findNewBooks(int days);
	Book findBookByBookId(String bookId);
	void save(Book book);
}
