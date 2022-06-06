package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
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

    //----------------------------------------Place The Order---------------------------
    @PostMapping("/add")
    public ResponseEntity<RespnseDTO> placeOrder(@RequestBody OrderDTO orderDTO,@RequestParam String token){
        RespnseDTO responseDTO = new RespnseDTO("Order Place Successful", iOrderService.placeOrder(orderDTO,token));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }

    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<RespnseDTO> getOrderById(@RequestParam String token) {
        RespnseDTO responseDTO = new RespnseDTO("Record found successfully", iOrderService.getOrderID(token));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.CREATED);
    }

    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<RespnseDTO> getAllOrders(){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iOrderService.getAllOrders());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    //----------------------------------------Cancel The Order---------------------------
    @PutMapping("/cancle/{id}")
    public ResponseEntity<RespnseDTO> cancelOrder(@PathVariable String token){
        RespnseDTO responseDTO = new RespnseDTO("Order Canceled", iOrderService.cancelOrder(token));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }
}
