package com.impetus.onlinebookstore.repository;

import com.impetus.onlinebookstore.entity.Purchase;
import com.impetus.onlinebookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {
    List<Purchase> findByUser(User user);
}
