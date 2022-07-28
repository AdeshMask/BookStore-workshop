package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService iCartService;

    @PostMapping("/add")
    public ResponseEntity<RespnseDTO> addToCart(@RequestBody CartDTO cartDTO, @RequestParam String token){
        RespnseDTO responseDTO = new RespnseDTO("Add record  Success", iCartService.addToCart(cartDTO,token));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<RespnseDTO> getAll(@RequestParam String token){
        RespnseDTO respnseDTO = new RespnseDTO("Here are all the Cart Items..." , iCartService.getCartItems(token));
        return new ResponseEntity<RespnseDTO>(respnseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<RespnseDTO> removeFromCart(@RequestParam String token, @PathVariable int id){
        RespnseDTO respnseDTO = new RespnseDTO("Here are all the Cart Items..." , iCartService.removeById(id,token));
        return new ResponseEntity<RespnseDTO>(respnseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/empty")
    public ResponseEntity<RespnseDTO> emptyCart(){
        RespnseDTO responseDTO = new RespnseDTO("Deleting all the cart Itmens",iCartService.emptyCart());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.ACCEPTED);
    }
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<RespnseDTO> updateQuantity(@RequestParam String token,@RequestBody CartDTO cartDTO,@PathVariable int id){
        RespnseDTO responseDTO = new RespnseDTO("Deleting all the cart Itmens",iCartService.updateQuantity(token,cartDTO,id));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/emptybyid/{token}")
    public ResponseEntity<RespnseDTO> emptyCartById(@RequestParam String token){
        RespnseDTO responseDTO = new RespnseDTO("Deleting all the cart Itmens",iCartService.deleteByUserId(token));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.ACCEPTED);
    }
}
