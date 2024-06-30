package com.impetus.onlinebookstore.serviceimp;

import com.impetus.onlinebookstore.entity.Book;
import com.impetus.onlinebookstore.entity.Cart;
import com.impetus.onlinebookstore.entity.Purchase;
import com.impetus.onlinebookstore.entity.User;
import com.impetus.onlinebookstore.exception.NoBookSelectedException;
import com.impetus.onlinebookstore.exception.NoUserPresentException;
import com.impetus.onlinebookstore.logger.Loggable;
import com.impetus.onlinebookstore.repository.PurchaseRepo;
import com.impetus.onlinebookstore.services.AuthService;
import com.impetus.onlinebookstore.services.BookService;
import com.impetus.onlinebookstore.services.CartService;
import com.impetus.onlinebookstore.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the PurchaseService interface.
 * Handles purchase-related operations.
 */
@Service
public class PurchaseServiceImp implements PurchaseService {
    @Autowired
    private AuthService authService;
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;
    @Autowired
    private PurchaseRepo purchaseRepo ;

    /**
     * Purchases books for a user.
     *
     * @param userId The ID of the user
     * @throws NoBookSelectedException if no book is selected
     * @throws NoUserPresentException if the user is not found
     */
    @Override
    @Loggable
    public void purchaseBook(Long userId){
        Optional<User> existUser = findUser(userId);
        Optional<Cart> userCart = findUserCart(userId);
        List<Book> bookList = userCart.get().getBooks();
        if(bookList.isEmpty()) throw new NoBookSelectedException("First add books to cart");
        for(Book book : bookList){
            Book newBook = new Book();
            newBook.setBookName(book.getBookName());
            newBook.setAuthorName(book.getAuthorName());
            newBook.setModifyDate(new Date());
            newBook.setCreateDate(book.getCreateDate());
            newBook.setPrice(book.getPrice());
            int quantity = book.getQuantity()-1 ;
            if(quantity>1) newBook.setQuantity(book.getQuantity()-1);
            bookService.saveBook(newBook);
        }
        Cart cart = userCart.get();
        cart.setBooks(new ArrayList<>());
        cart.setUser(existUser.get());
        cart.setCreateDate(userCart.get().getCreateDate());
        cart.setModifyDate(new Date());
        cartService.save(cart);
        Purchase purchase = new Purchase();
        purchase.setUser(existUser.get());
        purchase.setCreateDate(new Date());
        purchase.setModifyDate(new Date());
        purchase.setBooks(bookList);
        double amount =bookList.stream().mapToDouble(Book::getPrice).sum();
        purchase.setTotalAmount(amount);
        purchaseRepo.save(purchase);
        for(Book book : bookList){
            Book newBook = bookService.findByBookId(book.getId()).get();
//            newBook.setBookName(book.getBookName());
//            newBook.setAuthorName(book.getAuthorName());
            newBook.setModifyDate(new Date());
//            newBook.setCreateDate(book.getCreateDate());
//            newBook.setPrice(book.getPrice());
            int quantity = book.getQuantity()-1 ;
            if(quantity>1) newBook.setQuantity(book.getQuantity()-1);
            bookService.saveBook(newBook);
        }
    }

    /**
     * Retrieves purchase history for a user.
     *
     * @param userId The ID of the user
     * @return String containing purchase history
     * @throws NoUserPresentException if the user is not found
     */
    @Override
    @Loggable
    public String purchaseHistory(Long userId){
        Optional<User> existUser = findUser(userId);
        List<Purchase> purchaseOfUser = purchaseRepo.findByUser(existUser.get());
        List<String> orderHistory = new ArrayList<>();
        for(Purchase purchase : purchaseOfUser){
            orderHistory.add(purchase.getBooks().toString());
        }
        return orderHistory.toString();
    }

    /**
     * Finds a user by ID.
     *
     * @param userId The ID of the user
     * @return Optional<User> containing the user, if found
     * @throws NoUserPresentException if the user is not found
     */
    private Optional<User> findUser(Long userId){
        Optional<User> existUser = authService.findById(userId);
        if(!existUser.isPresent())
            throw new NoUserPresentException("No Such User is Present");
        return existUser;
    }

    /**
     * Finds a user's cart by ID.
     *
     * @param userId The ID of the user
     * @return Optional<Cart> containing the user's cart, if found
     * @throws NoBookSelectedException if the cart is empty
     */
    private Optional<Cart> findUserCart(Long userId){
        Optional<Cart> userCart =  cartService.cartOfUser(userId);
        if(!userCart.isPresent()) throw new NoBookSelectedException("Cart is Empty");
        return userCart;
    }
}
