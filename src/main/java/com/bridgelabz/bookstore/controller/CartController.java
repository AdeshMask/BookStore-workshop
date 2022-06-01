package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService iCartService;

    @PostMapping("/add")
    public ResponseEntity<RespnseDTO> addCart(@RequestBody CartDTO cartDTO) {
        RespnseDTO responseDTO = new RespnseDTO("Cart Item add Successfull", iCartService.addCart(cartDTO));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }
    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<RespnseDTO> getAddressById(@PathVariable Integer id) {
        RespnseDTO responseDTO = new RespnseDTO("Record found successfully", iCartService.getCartById(id));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.CREATED);
    }

    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<RespnseDTO> getAllCartItems(){
        RespnseDTO responseDTO = new RespnseDTO("Showing all the records..", iCartService.getAllCartItems());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    //-----------------------------------------Update-------------------------------
    @PutMapping("/edit/{id}")
    public ResponseEntity<RespnseDTO> editData(@PathVariable Integer id,@RequestBody  int quantity) {
        RespnseDTO responseDTO = new RespnseDTO("Successfully updated",iCartService.update(id,quantity));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RespnseDTO> deleteAddress(@PathVariable Integer id){
        RespnseDTO responseDTO = new RespnseDTO("Delete Operation Successfull", iCartService.removeById(id));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
}
