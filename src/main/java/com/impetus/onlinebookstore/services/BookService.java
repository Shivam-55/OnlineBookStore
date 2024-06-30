package com.impetus.onlinebookstore.services;

import com.impetus.onlinebookstore.dto.BookDto;
import com.impetus.onlinebookstore.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBook();
    void saveBook(Book book);
    Optional<Book> findByBookId(Long bookId);
    void addBook(BookDto bookDto);
    void updateBook(BookDto bookDto);
    BookDto bookToDto(Book book);
    Book dtoToBook(BookDto bookDto);
}
