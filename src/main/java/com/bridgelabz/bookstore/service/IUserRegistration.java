package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.module.UserRegistrationModule;
import org.springframework.stereotype.Service;

public interface IUserRegistration {
    Object addPerson(UserRegistrationModule newUserRegistartionModule);
}
