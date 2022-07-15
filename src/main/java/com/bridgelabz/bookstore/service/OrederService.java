package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.Cart;
import com.bridgelabz.bookstore.module.OrderData;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.IUsrRegistrationRepo;
import com.bridgelabz.bookstore.reository.OrderRepo;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrederService implements IOrderService{

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    IUserRegistration iUserRegistration;
    @Autowired
    ICartService iCartService;
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    IUsrRegistrationRepo iUsrRegistrationRepo;

    @Autowired
    EmailService emailService;
    @Autowired
    TokenUtility tokenUtility;

    @Override
    public OrderData placeOrder(OrderDTO orderDTO,String token) {
        UserRegistrationModule userData = iUserRegistration.getUserById(token);
        Cart cart = iCartService.getCartItems(token);
//        Object customerDetails = iCustomerService.getUserById(id);
        OrderData order = new OrderData(userData, cart);
//        emailService.sendEmail(userData.getEmailId(), "Order Created Successfully on ", "Order placed on" + " for books" + ". Total price is ");
        return orderRepo.save(order);
    }

    @Override
    public OrderData getOrderID(String token) {
        int id=tokenUtility.decodeToken(token);
        return orderRepo.findByIserId(id);
    }

    @Override
    public List<OrderData> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public OrderData cancelOrder(String token) {
        OrderData order = getOrderID(token);
        order.setCancle(false);
        return order;
    }
}
