package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.IUsrRegistrationRepo;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UseregistrationService implements IUserRegistration{

    @Autowired
    IUsrRegistrationRepo iUsrRegistrationRepo;

    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    EmailService emailService;


    @Override
    public Object addPerson(UserRegistrationModule newUserRegistartionModule) {
        UserRegistrationModule userRegistrationModule = new UserRegistrationModule(newUserRegistartionModule);
        iUsrRegistrationRepo.save(userRegistrationModule);
        String token=tokenUtility.createToken(userRegistrationModule.getId());
        emailService.sendEmail("adesh.maske@bridgelabz.com", "Token", "Registeration SuccessFull and generated token is--> "+token);
        return userRegistrationModule;
    }

    @Override
    public Object update(Integer id, UserDTO userDTO) {
        if (iUsrRegistrationRepo.findById(id).isPresent()) {
            UserRegistrationModule newUserRegistrationModule = new UserRegistrationModule(id, userDTO);
            UserRegistrationModule userRegistrationModule = iUsrRegistrationRepo.save(newUserRegistrationModule);
            return "Done " + userRegistrationModule;
        }
        else throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public String userLogin(LoginDTO loginDTO) {
        Optional<UserRegistrationModule> newUserRegistration = iUsrRegistrationRepo.findByEmailIdAndPassword(loginDTO.emailId, loginDTO.password);
        if (newUserRegistration.isPresent()) {
            return "LOGIN SUCCESSFUL";
        } else {
            System.out.println("User not Found Exception:");
            throw (new BookStoreExceptionHandler("Record not Found"));
        }
    }

    @Override
    public Object searchAll() {
        return iUsrRegistrationRepo.findAll();
    }

    @Override
    public UserRegistrationModule getUserById(String token) {
        int id=tokenUtility.decodeToken(token);
        return iUsrRegistrationRepo.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("User  with id " + id + " does not exist in database..!"));

    }
}
