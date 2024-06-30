package com.impetus.onlinebookstore.controller;

import com.impetus.onlinebookstore.dto.BookDto;
import com.impetus.onlinebookstore.entity.Book;
import com.impetus.onlinebookstore.logger.Loggable;
import com.impetus.onlinebookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller class for handling book-related requests.
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService ;

    /**
     * Fetches all books.
     *
     * @return ResponseEntity with a list of books
     */
    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> fetchAllBook(){
        List<Book> books = bookService.getAllBook();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Adds a new book.
     *
     * @param bookDto The BookDto object containing book information
     * @return ResponseEntity with a success message
     */
    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto){
        bookService.addBook(bookDto);
        return new ResponseEntity<>("Book Added Successfully",HttpStatus.OK);
    }

    /**
     * Updates the information of an existing book.
     *
     * @param bookDto The BookDto object containing updated book information
     * @return ResponseEntity with a success message
     */
    @PostMapping("/updateBook")
    public ResponseEntity<String> updateBook(@RequestBody BookDto bookDto){
        bookService.updateBook(bookDto);
        return new ResponseEntity<>("Book Info Updated Successfully",HttpStatus.OK);
    }
}
