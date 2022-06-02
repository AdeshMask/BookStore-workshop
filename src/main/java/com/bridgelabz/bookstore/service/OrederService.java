package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.OrderData;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.BookRepo;
import com.bridgelabz.bookstore.reository.IUsrRegistrationRepo;
import com.bridgelabz.bookstore.reository.OrderRepo;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    EmailService emailService;
    @Autowired
    TokenUtility tokenUtility;

    @Override
    public OrderData placeOrder(OrderDTO orderDTO) {
        ArrayList<BookModule> bookList = new ArrayList<>();
        UserRegistrationModule userData = iUserRegistration.getUserById(tokenUtility.createToken(orderDTO.getUserId()));
        List<Integer> bookIdList = orderDTO.bookId;
        List<Integer> quantity = orderDTO.quantity;
        int totalPrice = 0;
        for (int i = 0; i < bookIdList.size(); i++) {
            bookList.add(iBookService.getBookById(bookIdList.get(i)));
            totalPrice += iBookService.getBookById(bookIdList.get(i)).getPrice()* (quantity.get(i));
        }
        List<String> nameList = bookList.stream().map(BookModule::getBookName).collect(Collectors.toList());
        OrderData order = new OrderData(userData, orderDTO.getBookId() ,orderDTO.address, orderDTO.quantity);
        emailService.sendEmail(userData.getEmailId(), "Order Created Successfully!!", "Order placed for books" + nameList+"Total Price for it is "+totalPrice);
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
    public OrderData cancelOrder(int id) {
        OrderData order = getOrderID(id);
        order.setCancle(false);
        return order;
    }
}
