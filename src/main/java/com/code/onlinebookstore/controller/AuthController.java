package com.impetus.onlinebookstore.controller;

import com.impetus.onlinebookstore.dto.UserDto;
import com.impetus.onlinebookstore.entity.JwtResponse.JwtResponse;
import com.impetus.onlinebookstore.entity.User;
import com.impetus.onlinebookstore.logger.Loggable;
import com.impetus.onlinebookstore.security.JwtHelper;
import com.impetus.onlinebookstore.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * Controller class for handling authentication-related requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;

    /**
     * Handles the user registration request.
     *
     * @param userDto The UserDto object containing user registration information
     * @return ResponseEntity with a success message
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        authService.userRegistration(userDto);
        return new ResponseEntity<>("Successfully registration:"+userDto.getUserName(), HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user){
//        Optional<User> userInfo = authService.userLogin(user);
//        return new ResponseEntity<>( "Login successfully: "+userInfo.get().getUserName(),HttpStatus.OK);
//    }

    /**
     * Handles the user login request.
     *
     * @param request The JwtResponse object containing user login credentials
     * @return ResponseEntity with a JWT token upon successful login
     */
    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody JwtResponse request){
        this.doAuthenticate(request.getUserName(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.jwtHelper.generateToken(userDetails);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    /**
     * Authenticates the user with the provided username and password.
     *
     * @param userName The username
     * @param password The password
     */
    private void doAuthenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }
}
