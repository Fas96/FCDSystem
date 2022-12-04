package com.fas.fcdsystem.mockito.argument_captor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequest {
	private String title;
	private int price;
	private LocalDate publishedDate;
}
