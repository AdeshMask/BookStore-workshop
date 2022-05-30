package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.service.IUserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/user"))
public class UserRegistration {

    @Autowired
    IUserRegistration iUserRegistration;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Adesh";
    }

    /*--------------------Post Operation-------------------*/
    @PostMapping("/add")
    public ResponseEntity<RespnseDTO> addperson(@RequestBody UserDTO userDTO){
        UserRegistrationModule newUserRegistrationModule = new UserRegistrationModule(userDTO);
        RespnseDTO responseDTO = new RespnseDTO("Add record  Success", iUserRegistration.addPerson(newUserRegistrationModule));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }
}
