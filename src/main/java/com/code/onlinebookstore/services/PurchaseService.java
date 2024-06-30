
package com.code.onlinebookstore.services;

import com.code.onlinebookstore.entity.User;

public interface PurchaseService {
    void purchaseBook(Long userId);
    String purchaseHistory(Long userId);
}
