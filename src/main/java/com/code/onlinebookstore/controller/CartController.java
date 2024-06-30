package com.code.onlinebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.code.onlinebookstore.services.CartService;

import java.util.List;

/**
 * Controller class for handling cart-related requests.
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * Adds a book to the cart.
     *
     * @param userId The ID of the user
     * @param bookId The ID of the book
     * @return ResponseEntity with a success message
     */
    @PostMapping("/book/{bookId}/{userId}")
    public ResponseEntity<String> addToCart(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId)  {
        cartService.addToCart(userId,bookId);
        return new ResponseEntity<>("Added to cart", HttpStatus.OK);
    }

    /**
     * Removes a book from the cart.
     *
     * @param userId The ID of the user
     * @param bookId The ID of the book
     * @return ResponseEntity with a success message
     */
    @PostMapping("/removeBook/{bookId}/{userId}")
    public ResponseEntity<String> removeFromCart(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
        cartService.removeFromCart(userId,bookId);
        return new ResponseEntity<>("Book removed from your cart",HttpStatus.OK);
    }

    /**
     * Views the books in the cart.
     *
     * @param userId The ID of the user
     * @return ResponseEntity with a list of books in the cart
     */
    @GetMapping("/viewBooks/{userId}")
    public ResponseEntity<String> viewCart(@PathVariable("userId") Long userId){
        String books = cartService.viewCart(userId);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
}
