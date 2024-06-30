package com.impetus.onlinebookstore.repository;

import com.impetus.onlinebookstore.entity.Cart;
import com.impetus.onlinebookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
}
