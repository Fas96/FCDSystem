package com.fas.fcdsystem.mockito.argument_matchers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
	private String bookId;
	private String title;
	private int price;
	private LocalDate publishedDate;
	private boolean isDigital;
	


	public Book(String bookId, String title, int price, LocalDate publishedDate) {
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.publishedDate = publishedDate;
	}
	
	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return price == book.price &&
				Objects.equals(title, book.title) &&
				Objects.equals(publishedDate, book.publishedDate);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(title, price, publishedDate);
	}
}
