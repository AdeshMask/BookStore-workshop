package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.module.CartModule;
import com.bridgelabz.bookstore.module.OrderData;

public interface IOrderService {

    Object addBook(OrderData newBookModule);
}
