package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.dto.WishListDTO;
import com.bridgelabz.bookstore.module.WishList;
import com.bridgelabz.bookstore.service.IWishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    IWishList iWishList;

    /*--------------------Post Operation-------------------*/
    @PostMapping("/add")
    public ResponseEntity<RespnseDTO> addBook(@RequestBody WishListDTO wishListDTO,@RequestParam String token){
        RespnseDTO responseDTO = new RespnseDTO("Add record  Success", iWishList.addItem(wishListDTO,token));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }

    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get")
    public ResponseEntity<RespnseDTO> getWishList(@RequestParam String token) {
        RespnseDTO responseDTO = new RespnseDTO("Record found successfully", iWishList.getItemById(token));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.CREATED);
    }

    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<RespnseDTO> getAllOrders(){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iWishList.searchAll());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
    //---------------------------------------Delete---------------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RespnseDTO> deleteAddress(@PathVariable Integer id){
        RespnseDTO responseDTO = new RespnseDTO("Delete Operation Successful", iWishList.removeById(id));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
    //-----------------------------------------Update-------------------------------
    @PutMapping("/edit/{id}")
    public ResponseEntity<RespnseDTO> editData(@PathVariable Integer id,@RequestBody WishListDTO wishListDTO) {
        RespnseDTO responseDTO = new RespnseDTO("Successfully updated",iWishList.update(id,wishListDTO));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
}
