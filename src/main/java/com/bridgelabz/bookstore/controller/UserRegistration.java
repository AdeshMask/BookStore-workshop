package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.service.IUserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(allowedHeaders = "*", origins = "*")
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
        RespnseDTO responseDTO = new RespnseDTO("Add record  Success", iUserRegistration.addPerson(userDTO));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }
    //-----------------------------------------Update-------------------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<RespnseDTO> editData(@PathVariable Integer id,@RequestBody UserDTO userDTO) {
        RespnseDTO responseDTO = new RespnseDTO("Successfully updated",iUserRegistration.update(id,userDTO));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }



    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<RespnseDTO> getAll(){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iUserRegistration.searchAll());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/get/{token}")
    public ResponseEntity<RespnseDTO> getById(@RequestParam String token){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iUserRegistration.getUserById(token));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    @PostMapping("/userlogin")
    public ResponseEntity<RespnseDTO> userLogin(@RequestBody LoginDTO loginDTO) {
        Optional<UserRegistrationModule> login = iUserRegistration.userLogin(loginDTO);
        if (login != null) {
            RespnseDTO respnseDTO = new RespnseDTO("LOGIN SUCCESSFUL", login);
            return new ResponseEntity<RespnseDTO>(respnseDTO,HttpStatus.ACCEPTED);
        } else {
            RespnseDTO respnseDTO = new RespnseDTO("User login not successfully", login);
            throw (new BookStoreExceptionHandler("Record not Found"));
        }
    }
}
