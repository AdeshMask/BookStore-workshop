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

    @PostMapping("/add")
    public ResponseEntity<RespnseDTO> placeOrder(@RequestBody OrderDTO orderDTO){
        RespnseDTO responseDTO = new RespnseDTO("Add record  Success", iOrderService.placeOrder(orderDTO));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }

    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<RespnseDTO> getOrderById(@PathVariable Integer id) {
        RespnseDTO responseDTO = new RespnseDTO("Record found successfully", iOrderService.getOrderID(id));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.CREATED);
    }

    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<RespnseDTO> getAllOrders(){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iOrderService.getAllOrders());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    @PutMapping("/cancle/{id}")
    public ResponseEntity<RespnseDTO> cancleOrder(@PathVariable int id){
        RespnseDTO responseDTO = new RespnseDTO("Order Cancled", iOrderService.cancleOrder(id));
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
    }
}
