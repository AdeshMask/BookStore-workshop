package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.controller.UserRegistration;
import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.OrderData;
import com.bridgelabz.bookstore.module.UserRegistrationModule;

import java.util.List;

public interface IOrderService {

    OrderData placeOrder(OrderDTO orderDTO,String token,int id);

    OrderData getOrderID(String token);

    List<OrderData> getAllOrders();

    OrderData cancelOrder(String token);
}
