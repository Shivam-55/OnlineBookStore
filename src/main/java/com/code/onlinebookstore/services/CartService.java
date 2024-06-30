package com.impetus.onlinebookstore.services;

import com.impetus.onlinebookstore.entity.Book;
import com.impetus.onlinebookstore.entity.Cart;
import com.impetus.onlinebookstore.entity.User;
import com.impetus.onlinebookstore.exception.AlreadyBookPresentException;
import com.impetus.onlinebookstore.exception.NoBookPresentException;
import com.impetus.onlinebookstore.exception.NoUserPresentException;

import java.util.List;
import java.util.Optional;

public interface CartService {
    void addToCart(Long userId, Long bookId) ;
    void removeFromCart(Long userId, Long bookId);
    String viewCart(Long userId);
    Optional<Cart> cartOfUser(Long userId);
    void save(Cart cart);
}
