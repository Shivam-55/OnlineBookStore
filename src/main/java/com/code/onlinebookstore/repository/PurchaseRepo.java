

package com.code.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.onlinebookstore.entity.Purchase;
import com.code.onlinebookstore.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {
    List<Purchase> findByUser(User user);
}
