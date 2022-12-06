package com.fas.fcdsystem.mockito.spies;

import com.fas.fcdsystem.mockito.common.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpiesBookManagerTest {

    @InjectMocks
    private BookManager bookManager;

    @Spy
    private BookService bookService;

    @Test
    public void testMockitoSpy(){
        //we can create a spy with the spy() method by using annotation @Spy
//        BookService bookService =  spy(BookService.class);
//        BookManager bookManager = new BookManager(bookService);
        // We need to mock findBook because it is communicating to database or not implemented
        // We need to call getAppliedDiscount to calculate the discounted price
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
//        doReturn(book).when(bookService).findBook("1234");
        // TODO: 2020-11-30 1. we can use doReturn() to stub the method
        //when performing a spy, we can use doReturn() or doThrow() to stub the method
        //using when() will not work because it actually calls the method
        //
		when(bookService.findBook("1234")).thenReturn(book);
        int actualDiscount = bookManager.applyDiscountOnBook("1234", 10);
        assertEquals(450, actualDiscount);
    }
    @Test
    public void testMockitoSpyWithRealMethod() {
        //We need to mock findBook because it is communicating to database or not implemented
        //We need to call getAppliedDiscount to calculate the discounted price
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        doReturn(book).when(bookService).findBook("1234");
        int actualDiscount = bookManager.applyDiscountOnBook("1234", 10);
        assertEquals(450, actualDiscount);
    }


}