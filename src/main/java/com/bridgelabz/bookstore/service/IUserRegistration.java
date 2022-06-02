package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.module.UserRegistrationModule;

public interface IUserRegistration {
    Object addPerson(UserRegistrationModule newUserRegistartionModule);

    Object update(Integer id, UserDTO userDTO);

    String userLogin(LoginDTO loginDTO);

    Object searchAll();

    UserRegistrationModule getUserById(String token);

//    Optional<UserRegistration> userLogin(LoginDTO loginDTO);
}
