package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.module.CartModule;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService iCartService;

    @PostMapping("/add")
    public ResponseEntity<RespnseDTO> addCart(@RequestBody CartDTO cartDTO) {
        RespnseDTO responseDTO = new RespnseDTO("Success", iCartService.addCart(cartDTO));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }
}
