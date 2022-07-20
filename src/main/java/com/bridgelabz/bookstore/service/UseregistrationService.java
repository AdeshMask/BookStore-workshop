package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.IUsrRegistrationRepo;
import com.bridgelabz.bookstore.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UseregistrationService implements IUserRegistration{

    @Autowired
    IUsrRegistrationRepo iUsrRegistrationRepo;

    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    EmailService emailService;


    @Override
    public Object addPerson(UserDTO userDTO) {
        UserRegistrationModule user = new UserRegistrationModule(userDTO);
        iUsrRegistrationRepo.save(user);
        String token=tokenUtility.createToken(user.getId());
        user.setToken(token);
        iUsrRegistrationRepo.save(user);
        emailService.sendEmail(user.getEmailId(), "Token",
                "--> "+token);
        return user;
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
    public Optional<UserRegistrationModule> userLogin(LoginDTO loginDTO) {
        Optional<UserRegistrationModule> newUserRegistration =
                iUsrRegistrationRepo.findByEmailIdAndPassword(loginDTO.emailId, loginDTO.password);
        if (newUserRegistration.isPresent()) {
             log.info("LOGIN SUCCESSFUL");
             return newUserRegistration;
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

    @Override
    public UserRegistrationModule getUserId(int id) {
        return iUsrRegistrationRepo.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("User  with id " + id + " does not exist in database..!"));

    }
}
