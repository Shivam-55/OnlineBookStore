package com.code.onlinebookstore.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.code.onlinebookstore.entity.User;
import com.code.onlinebookstore.repository.UserRepo;

/**
 * Custom implementation of Spring Security's UserDetailsService interface.
 * Loads user details by username from the repository.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    /**
     * Load user details by username from the repository.
     *
     * @param username The username of the user
     * @return UserDetails object containing user details
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username).orElseThrow(()->new RuntimeException("User No Found"));
        return user;
    }
}
