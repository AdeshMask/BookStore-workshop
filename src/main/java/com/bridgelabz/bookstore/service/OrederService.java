package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.module.OrderData;
import com.bridgelabz.bookstore.reository.OrderReo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrederService implements IOrderService{

    @Autowired
    OrderReo orderReo;


    @Override
    public Object addBook(OrderData newBookModule) {
        return null;
    }
}
