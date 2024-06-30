package com.code.onlinebookstore.serviceimp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code.onlinebookstore.dto.UserDto;
import com.code.onlinebookstore.entity.User;
import com.code.onlinebookstore.exception.InvalidPasswordException;
import com.code.onlinebookstore.exception.InvalidUserNameException;
import com.code.onlinebookstore.exception.NoUserPresentException;
import com.code.onlinebookstore.logger.Loggable;
import com.code.onlinebookstore.repository.UserRepo;
import com.code.onlinebookstore.services.AuthService;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the AuthService interface.
 */
@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private UserRepo registerUser ;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper mapper ;

    /**
     * Registers a new user.
     *
     * @param userDto The user DTO containing user registration details
     * @throws InvalidUserNameException If the username already exists
     */
    @Override
    @Loggable
    public void userRegistration(UserDto userDto) {
        User user = dtoToUser(userDto);
        Optional<User> exists = registerUser.findByUserName(user.getUserName());
        if(exists.isPresent()) throw new InvalidUserNameException("Already UserName Present");
        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        registerUser.save(user);
    }

    /**
     * Performs user login.
     *
     * @param user The user object containing login credentials
     * @return Optional of the user if login is successful
     * @throws InvalidUserNameException If the username does not exist
     * @throws InvalidPasswordException If the password is incorrect
     */
    @Override
    @Loggable
    public Optional<User> userLogin(User user){
        Optional<User> exist = registerUser.findByUserName(user.getUserName());

        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        if(!exist.isPresent()){
            throw new InvalidUserNameException("Entered Wrong Username");
        }else{
            String password = exist.get().getPassword();
            if(!Objects.equals(password, user.getPassword())) throw new InvalidPasswordException("Invalid password");
        }
        return exist;
    }

    /**
     * Finds a user by ID.
     *
     * @param userId The ID of the user to find
     * @return Optional of the found user
     * @throws NoUserPresentException If no user is found with the given ID
     */
    @Override
    public Optional<User> findById(long userId) {
        Optional<User> user = registerUser.findById(userId);
        if(!user.isPresent()) throw new NoUserPresentException("No User is Present");
        return user;
    }

    // User to Dto
    public UserDto userToDto(User user) {
        return this.mapper.map(user, UserDto.class);
    }

    // Dto to User
    public User dtoToUser(UserDto userDto) {
        return this.mapper.map(userDto, User.class);
    }
}
