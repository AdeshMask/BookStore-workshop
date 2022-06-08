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
    public OrderData placeOrder(OrderDTO orderDTO,String token) {
        ArrayList<BookModule> bookList = new ArrayList<>();
        UserRegistrationModule userData = iUserRegistration.getUserById(token);
        List<Integer> bookIdList = orderDTO.bookId;
        List<Integer> quantity = orderDTO.quantity;
        int totalPrice = 0;
        for (int i = 0; i < bookIdList.size(); i++) {
            if (quantity.get(i) <= iBookService.getBookById(bookIdList.get(i)).getBookQuantity()) {
                bookList.add(iBookService.getBookById(bookIdList.get(i)));
                totalPrice += iBookService.getBookById(bookIdList.get(i)).getPrice() * (quantity.get(i));
                iBookService.updateQuantityById(bookIdList.get(i), iBookService.getBookById(bookIdList.get(i)).getBookQuantity() - (quantity.get(i)), token);
            } else
                throw new BookStoreExceptionHandler("Please select a small quantity to order as stocks are limited: Current stock for book id: "
                        + bookIdList.get(i) + " is " + iBookService.getBookById(bookIdList.get(i)).getBookQuantity() + ".");
        }
        List<String> nameList = bookList.stream().map(BookModule::getBookName).toList();
        OrderData order = new OrderData(userData, orderDTO.getBookId(), orderDTO.address, orderDTO.getQuantity(),totalPrice);

        emailService.sendEmail(userData.getEmailId(), "Order Created Successfully on ", "Order placed on" + " for books" + nameList + ". Total price is " + totalPrice);
        return orderRepo.save(order);
    }

    @Override
    public OrderData getOrderID(String token) {
        int id=tokenUtility.decodeToken(token);
        return orderRepo.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("Book  with id " + id +
                " does not exist in database..!"));
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
