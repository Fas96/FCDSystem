package com.fas.fcdsystem.mockito.behavior.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BehaviorVerificationBookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Spy
    private BookRepository bookRepository;

    @Test
    public void testAddBook() {
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(book);
        verify(bookRepository).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
        bookService.addBook(bookRequest);
        verify(bookRepository, times(0)).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice1() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
        verify(bookRepository, times(2)).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice2() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
        bookService.addBook(bookRequest);
        verify(bookRepository, never()).save(book);
    }

    @Test
    public void testUpdatePrice() {
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());

        doReturn(book).when(bookRepository).findBookById("1234");
        bookService.updatePrice("1234", 600);
        verify(bookRepository).save(book);
    }
    @Test
    public void testUpdatePriceNoInteraction() {
       bookService.updatePrice(null, 600);
        verifyNoInteractions(bookRepository);
    }
    @Test
    public void testUpdatePriceNoInteractionWithSave() {
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
         when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 500);
        verify(bookRepository).findBookById("1234");
        verifyNoMoreInteractions(bookRepository);
        verify(bookRepository, never()).save(book);
    }

    @Test
    public void testUpdatePrice2() {
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 500);
        verify(bookRepository).findBookById("1234");
        //we can add this verification if we did not check for the current price with updated price
//        verify(bookRepository).save(book);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testUpdatePrice3() {
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 500);

        InOrder inOrder = Mockito.inOrder(bookRepository);
        inOrder.verify(bookRepository).findBookById("1234");
        inOrder.verify(bookRepository).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice3() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
//		verify(bookRepository, times(2)).save(book);
//		verify(bookRepository, atLeast(4)).save(book);
//		verify(bookRepository, atMost(2)).save(book);
//		verify(bookRepository, atMostOnce()).save(book);
        verify(bookRepository, atLeastOnce()).save(book);
    }

}