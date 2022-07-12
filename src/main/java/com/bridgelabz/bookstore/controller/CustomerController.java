package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CustomerDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    ICustomerService iCustomerService;

    /*--------------------Post Operation-------------------*/
    @PostMapping("/add")
    public ResponseEntity<RespnseDTO> addperson(@RequestBody CustomerDTO customerDTO){
        RespnseDTO responseDTO = new RespnseDTO("Add record  Success", iCustomerService.addCustomerDetails(customerDTO));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RespnseDTO> getById(@PathVariable int id){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iCustomerService.getUserById(id));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
}
