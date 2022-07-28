package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.module.*;
import com.bridgelabz.bookstore.reository.BookRepo;
import com.bridgelabz.bookstore.reository.CartRepo;
import com.bridgelabz.bookstore.reository.IUsrRegistrationRepo;
import com.bridgelabz.bookstore.reository.OrderRepo;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    BookRepo bookRepo;
    @Autowired
    IBookService iBookService;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    EmailService emailService;
    @Autowired
    TokenUtility tokenUtility;

    @Override
    public List<OrderData> placeOrder(OrderDTO orderDTO, String token) {
        List<OrderData> list = new ArrayList<>();
        UserRegistrationModule userData = iUserRegistration.getUserById(token);
        List<Cart> cart = iCartService.getCartItems(token);
        List<BookModule> bookData = new ArrayList<>();
        int quantity = 0;
        double totalPrice = 0;
        for (int i = 0;i < cart.size();i++){
            quantity = quantity + cart.get(i).getQuantity();
            totalPrice = totalPrice + cart.get(i).getTotalPrice();
            bookData.add(cart.get(i).getBook());
        }
        CustomerDetails customerDetails = iCustomerService.getUserById(orderDTO.getCustId());
        OrderData order = new OrderData(userData, cart, customerDetails,bookData, quantity,totalPrice);
//        emailService.sendEmail(userData.getEmailId(), "Order Created Successfully on ", "Order placed on" + " for books" + ". Total price is ");
        list.add(order);
        orderRepo.saveAll(list);
        iCartService.emptyCart();
        return list;
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
        order.setCancel(false);
        return order;
    }
}
