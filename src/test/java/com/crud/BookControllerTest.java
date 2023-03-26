package com.crud;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.crud.api.BookController;
import com.crud.entity.Book;
import com.crud.repository.BookRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    private List<Book> bookList;

    @BeforeEach
    public void setUp() {
        bookList = new ArrayList<>();
        bookList.add(new Book(1L, "Book 1", "Author 1"));
        bookList.add(new Book(2L, "Book 2", "Author 2"));
    }

    @Test
    public void getAllBooksTest() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> result = bookController.getAllBooks();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void addBookTest() {
        Book book = new Book(3L, "Book 3", "Author 3");
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        ResponseEntity<Book> response = bookController.addBook(book);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(book, response.getBody());
    }

    @Test
    public void deleteBookTest() {
        Long bookId = 1L;
        doNothing().when(bookRepository).deleteById(bookId);
        ResponseEntity<Void> response = bookController.deleteBook(bookId);
        verify(bookRepository).deleteById(bookId);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void updateBookTest() {
        Long bookId = 1L;
        Book book = new Book(bookId, "Updated Book 1", "Updated Author 1");
        when(bookRepository.existsById(bookId)).thenReturn(true);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        ResponseEntity<Book> response = bookController.updateBook(bookId, book);
        verify(bookRepository).save(book);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(book, response.getBody());
    }
}
