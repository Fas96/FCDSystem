package com.fas.fcdsystem.mockito.bdd.stubbing;

import java.util.List;

public interface BookRepository {
	List<Book> findNewBooks(int days);
}
