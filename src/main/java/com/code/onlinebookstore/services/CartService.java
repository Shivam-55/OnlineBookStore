package com.code.onlinebookstore.services;

import java.util.List;
import java.util.Optional;

import com.code.onlinebookstore.entity.Book;
import com.code.onlinebookstore.entity.Cart;
import com.code.onlinebookstore.entity.User;
import com.code.onlinebookstore.exception.AlreadyBookPresentException;
import com.code.onlinebookstore.exception.NoBookPresentException;
import com.code.onlinebookstore.exception.NoUserPresentException;

public interface CartService {
    void addToCart(Long userId, Long bookId) ;
    void removeFromCart(Long userId, Long bookId);
    String viewCart(Long userId);
    Optional<Cart> cartOfUser(Long userId);
    void save(Cart cart);
}
