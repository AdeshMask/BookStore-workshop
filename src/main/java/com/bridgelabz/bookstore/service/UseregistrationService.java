package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.IUsrRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UseregistrationService implements IUserRegistration{

    @Autowired
    IUsrRegistrationRepo iUsrRegistrationRepo;

    @Override
    public Object addPerson(UserRegistrationModule newUserRegistartionModule) {
        UserRegistrationModule addressBookModule = new UserRegistrationModule(newUserRegistartionModule);
        iUsrRegistrationRepo.save(addressBookModule);
        return addressBookModule;
    }
}
