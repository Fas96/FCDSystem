package com.fas.fcdsystem.mockito.test_doubles.dummy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
	private String bookId;
	private String title;
	private int price;
	private LocalDate publishedDate;
}
