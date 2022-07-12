package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.module.UserRegistrationModule;

import java.util.Optional;

public interface IUserRegistration {
    Object addPerson(UserDTO userDTO);

    Object update(Integer id, UserDTO userDTO);

    Optional<UserRegistrationModule> userLogin(LoginDTO loginDTO);

    Object searchAll();

    UserRegistrationModule getUserById(String token);

    UserRegistrationModule getUserId(int userId);

//    Optional<UserRegistration> userLogin(LoginDTO loginDTO);
}
