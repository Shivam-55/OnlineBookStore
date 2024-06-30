package com.code.onlinebookstore.serviceimp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.onlinebookstore.entity.Book;
import com.code.onlinebookstore.entity.Cart;
import com.code.onlinebookstore.entity.User;
import com.code.onlinebookstore.exception.AlreadyBookPresentException;
import com.code.onlinebookstore.exception.NoBookPresentException;
import com.code.onlinebookstore.exception.NoBookSelectedException;
import com.code.onlinebookstore.exception.NoUserPresentException;
import com.code.onlinebookstore.logger.Loggable;
import com.code.onlinebookstore.repository.CartRepo;
import com.code.onlinebookstore.services.AuthService;
import com.code.onlinebookstore.services.BookService;
import com.code.onlinebookstore.services.CartService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CartService interface.
 */
@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepo cartRepo ;
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthService authService;

    /**
     * Adds a book to the user's cart.
     *
     * @param userId The ID of the user
     * @param bookId The ID of the book to add to the cart
     */
    @Override
    @Loggable
    public void addToCart(Long userId, Long bookId) {
        Optional<User> existUser = findUser(userId);

        Optional<Book> optionalBook = findBook(bookId);
        Book book = optionalBook.get();

        Optional<Cart> optionalCart = cartRepo.findByUser(existUser.get());
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<Book> books = cart.getBooks();

            for (Book availBook : books) {
                if (availBook.getId() == bookId) {
                    throw new AlreadyBookPresentException("Already Book Present");
                }
            }
            books.add(book);
            book.getCart().add(cart); // Adding cart to book's cart list
            bookService.saveBook(book);
            cart.setModifyDate(new Date());
            cart.setBooks(books);
            cartRepo.save(cart);
        } else {
            Cart newCart = new Cart();
            List<Book> books = new ArrayList<>();
            books.add(book);
            newCart.setUser(existUser.get());
            newCart.setBooks(books);
            newCart.setCreateDate(new Date());
            newCart.setModifyDate(new Date());
            book.getCart().add(newCart); // Adding cart to book's cart list
            bookService.saveBook(book);
            cartRepo.save(newCart);
        }
    }

    /**
     * Removes a book from the user's cart.
     *
     * @param userId The ID of the user
     * @param bookId The ID of the book to remove from the cart
     */
    @Override
    @Loggable
    public void removeFromCart(Long userId, Long bookId) {
        Optional<User> existUser = findUser(userId);
        Optional<Book> existBook = findBook(bookId);
        Optional<Cart> optionalCart = cartRepo.findByUser(existUser.get());
        if(optionalCart.isPresent()){
            List<Book> bookList = optionalCart.get().getBooks();
            List<Book> newBookList = new ArrayList<>();
            for(Book book : bookList){
                if(book.getId()!=existBook.get().getId()){
                    newBookList.add(book);
                }
            }
            Cart cart =optionalCart.get();
            cart.setBooks(newBookList);
            cart.setModifyDate(new Date());
//            cart.setUser(existUser.get());
//            cart.setCreateDate(optionalCart.get().getCreateDate());
            cartRepo.save(cart);
        }
        else{
            throw new NoBookSelectedException("you have not selected any book yet");
        }
    }

    /**
     * Retrieves the list of books in the user's cart.
     *
     * @param userId The ID of the user
     * @return A string representation of the books in the cart
     */
    @Override
    @Loggable
    public String viewCart(Long userId) {
        Optional<User> existUser = findUser(userId);
        Optional<Cart> cartOfUser = cartRepo.findByUser(existUser.get());
        if(!cartOfUser.isPresent()) return "Cart is Empty";
        else return cartOfUser.get().getBooks().toString();
    }

    /**
     * Retrieves the cart of the given user.
     *
     * @param userId The ID of the user
     * @return The cart of the user
     */
    @Override
    @Loggable
    public Optional<Cart> cartOfUser(Long userId) {
        Optional<User>user=findUser(userId);
        Optional<Cart> cart = cartRepo.findByUser(user.get());
        if(!cart.isPresent()) throw new NoBookSelectedException("Cart is Empty");
        return cart;
    }

    /**
     * Saves the cart to the database.
     *
     * @param cart The cart to be saved
     */
    @Override
    public void save(Cart cart) {
        cartRepo.save(cart);
    }

    /**
     * Finds a user by the given user ID.
     *
     * @param userId The ID of the user to find
     * @return An Optional containing the user if found, otherwise empty
     * @throws NoUserPresentException if no user is found with the given ID
     */
    private Optional<User> findUser(Long userId){
        Optional<User> existUser = authService.findById(userId);
        if (!existUser.isPresent()) {
            throw new NoUserPresentException("No User Present");
        }
        return existUser;
    }

    /**
     * Finds a book by the given book ID.
     *
     * @param bookId The ID of the book to find
     * @return An Optional containing the book if found, otherwise empty
     * @throws NoBookPresentException if no book is found with the given ID
     */
    private Optional<Book> findBook(Long bookId){
        Optional<Book> optionalBook = bookService.findByBookId(bookId);

        if (!optionalBook.isPresent()) {
            throw new NoBookPresentException("No Book present");
        }
        return  optionalBook;
    }
}
