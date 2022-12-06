package com.fas.fcdsystem.mockito.stubbing;

import com.fas.fcdsystem.mockito.common.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StubbingBookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testCalculateTotalCostOfBooks() {
        List<String> bookIds = new ArrayList<>();
        bookIds.add("1234");
        bookIds.add("1234");

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 In Action", 400, LocalDate.now());

        //stubbing used here is used to force what the mock object will return when a method is called
//        when(bookRepository.findBookByBookId("1234")).thenReturn(book1);
//        when(bookRepository.findBookByBookId("1235")).thenReturn(book2);

        //alternative way to stub
        when(bookRepository.findBookByBookId("1234"))
                .thenReturn(book1)
                .thenReturn(book1);
//        when(bookRepository.findBookByBookId("1235")).thenReturn(book2);
//        doReturn(book2).when(bookRepository).findBookByBookId("1235");

        int actualCost = bookService.calculateTotalCost(bookIds);
        assertEquals(900, actualCost);
    }

    @Test
    public void testSaveBook() {
        Book book1 = new Book(null, "Mockito In Action", 500, LocalDate.now());
        doNothing().when(bookRepository).save(book1); // ==
        verify(bookRepository, times(0)).save(book1);
        bookService.addBook(book1);
    }

    @Test
    public void testSaveBookWithBookRequest() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        doNothing().when(bookRepository).save(book);
        bookService.addBook(bookRequest);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
//		doNothing().when(bookRepository).save(book);
        bookService.addBook(bookRequest);
    }
}