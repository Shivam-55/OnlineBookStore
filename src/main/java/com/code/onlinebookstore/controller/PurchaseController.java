





package com.code.onlinebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.code.onlinebookstore.entity.User;
import com.code.onlinebookstore.services.PurchaseService;

/**
 * Controller class for handling purchase-related requests.
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
     * Handles the purchase of a book by a user.
     *
     * @param userId The ID of the user
     * @return ResponseEntity with a success message
     */
    @PostMapping("/book/{userId}")
    public ResponseEntity<String> purchaseBook(@PathVariable("userId") Long userId){
        purchaseService.purchaseBook(userId);
        return new ResponseEntity<>("Purchase Successfully", HttpStatus.OK);
    }

    /**
     * Retrieves the purchase history of a user.
     *
     * @param userId The ID of the user
     * @return ResponseEntity with the purchase history
     */
    @GetMapping("/history/{userId}")
    public  ResponseEntity<String> purchaseHistory(@PathVariable("userId") Long userId){
        String history = purchaseService.purchaseHistory(userId);
        return new ResponseEntity<>(history,HttpStatus.OK);
    }
}
