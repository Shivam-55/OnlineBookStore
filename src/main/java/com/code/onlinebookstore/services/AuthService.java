package com.code.onlinebookstore.services;

import java.util.Optional;

import com.code.onlinebookstore.dto.UserDto;
import com.code.onlinebookstore.entity.User;

public interface AuthService {
    void userRegistration(UserDto userDto);
    Optional<User> userLogin(User user);
    User dtoToUser(UserDto userDto);
    UserDto userToDto(User user);
    Optional<User> findById(long id);
}
