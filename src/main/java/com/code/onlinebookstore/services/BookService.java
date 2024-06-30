package com.code.onlinebookstore.services;

import java.util.List;
import java.util.Optional;

import com.code.onlinebookstore.dto.BookDto;
import com.code.onlinebookstore.entity.Book;

public interface BookService {
    List<Book> getAllBook();
    void saveBook(Book book);
    Optional<Book> findByBookId(Long bookId);
    void addBook(BookDto bookDto);
    void updateBook(BookDto bookDto);
    BookDto bookToDto(Book book);
    Book dtoToBook(BookDto bookDto);
}
