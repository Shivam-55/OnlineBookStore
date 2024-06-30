package com.impetus.onlinebookstore.serviceimp;

import com.impetus.onlinebookstore.dto.BookDto;
import com.impetus.onlinebookstore.entity.Book;
import com.impetus.onlinebookstore.exception.NoSuchBookAvailableException;
import com.impetus.onlinebookstore.logger.Loggable;
import com.impetus.onlinebookstore.repository.BookRepo;
import com.impetus.onlinebookstore.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the BookService interface.
 */
@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepo bookRepo ;
    @Autowired
    private ModelMapper mapper ;

    /**
     * Retrieves all books from the database.
     *
     * @return List of all books
     */
    @Override
    public List<Book> getAllBook() {
        return bookRepo.findAll();
    }

    /**
     * Saves a book to the database.
     *
     * @param book The book to be saved
     */
    @Override
    public void saveBook(Book book) {
        bookRepo.save(book);
    }

    /**
     * Finds a book by its ID.
     *
     * @param bookId The ID of the book to find
     * @return Optional of the found book
     */
    @Override
    public Optional<Book> findByBookId(Long bookId) {
        Optional<Book> book = bookRepo.findBookById(bookId);
        return book;
    }

    /**
     * Adds a new book to the database.
     *
     * @param bookDto The DTO containing book information
     */
    @Override
    @Loggable
    public void addBook(BookDto bookDto) {
        Book book = dtoToBook(bookDto);
        book.setCreateDate(new Date());
        book.setModifyDate(new Date());
        bookRepo.save(book);
    }

    /**
     * Updates book information in the database.
     *
     * @param bookDto The DTO containing updated book information
     */
    @Override
    @Loggable
    public void updateBook(BookDto bookDto){
        Book book = dtoToBook(bookDto);
        Optional<Book> bookExist = bookRepo.findBookById(book.getId());
        if(!bookExist.isPresent()) throw new NoSuchBookAvailableException("Wrong Book Selected");
        book.setCreateDate(bookExist.get().getCreateDate());
        book.setModifyDate(new Date());
        book.setBookName(book.getBookName());
        book.setAuthorName(book.getAuthorName());
        bookRepo.save(book);
    }

    /**
     * Converts a Book entity to a BookDto.
     *
     * @param book The Book entity to convert
     * @return The converted BookDto
     */
    @Override
    public BookDto bookToDto(Book book) {
        return this.mapper.map(book,BookDto.class);
    }

    /**
     * Converts a BookDto to a Book entity.
     *
     * @param bookDto The BookDto to convert
     * @return The converted Book entity
     */
    @Override
    public Book dtoToBook(BookDto bookDto) {
        return this.mapper.map(bookDto,Book.class);
    }

}
