package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.OrderData;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.BookRepo;
import com.bridgelabz.bookstore.reository.IUsrRegistrationRepo;
import com.bridgelabz.bookstore.reository.OrderRepo;
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
    BookRepo bookRepo;
    @Autowired
    IBookService iBookService;
    @Autowired
    IUsrRegistrationRepo iUsrRegistrationRepo;

    @Override
    public OrderData placeOrder(OrderDTO orderDTO) {
        UserRegistrationModule userData = iUserRegistration.getUserById(orderDTO.getUserId());
        BookModule book = iBookService.getBookById(orderDTO.getBookId());
        float totalPrice = book.getPrice() * book.getBookQuantity();
        OrderData order = new OrderData(userData, book, orderDTO.address,totalPrice);
        return orderRepo.save(order);
    }

    @Override
    public OrderData getOrderID(Integer id) {
        return orderRepo.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("Book  with id " + id + " does not exist in database..!"));
    }

    @Override
    public List<OrderData> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public OrderData cancleOrder(int id) {
        OrderData order = getOrderID(id);
        order.setCancle(false);
        return order;
    }
}
