package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.OrderData;
import com.bridgelabz.bookstore.service.ICartService;
import com.bridgelabz.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService iOrderService;

//    @PostMapping("/placeorder")
//    public ResponseEntity<RespnseDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
//        RespnseDTO responseDTO = new RespnseDTO("Success", iOrderService.placeOrder(orderDTO));
//        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
//    }

    @PostMapping("/add")
    public ResponseEntity<RespnseDTO> addBook(@RequestBody OrderDTO orderDTO){
        OrderData newBookModule = new OrderData(orderDTO);
        RespnseDTO responseDTO = new RespnseDTO("Add record  Success", iOrderService.addBook(newBookModule));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }
}
