package com.impetus.onlinebookstore.services;

import com.impetus.onlinebookstore.entity.User;

public interface PurchaseService {
    void purchaseBook(Long userId);
    String purchaseHistory(Long userId);
}
