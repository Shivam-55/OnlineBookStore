package com.impetus.onlinebookstore.services;

import com.impetus.onlinebookstore.dto.UserDto;
import com.impetus.onlinebookstore.entity.User;

import java.util.Optional;

public interface AuthService {
    void userRegistration(UserDto userDto);
    Optional<User> userLogin(User user);
    User dtoToUser(UserDto userDto);
    UserDto userToDto(User user);
    Optional<User> findById(long id);
}
