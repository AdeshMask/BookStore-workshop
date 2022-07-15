package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.Cart;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.BookRepo;
import com.bridgelabz.bookstore.reository.CartRepo;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServices implements ICartService{

    @Autowired
    IUserRegistration iUserRegistration;
    @Autowired
    IBookService iBookService;

    @Autowired
    EmailService emailService;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    BookRepo bookRepo;

    @Autowired
    TokenUtility tokenUtility;
    @Override
    public Cart addToCart(CartDTO cartDTO, String token) {
        UserRegistrationModule userData = iUserRegistration.getUserById(token);
        BookModule bookData = iBookService.getBookById(cartDTO.bookId);
        int bookQuantity = cartDTO.quantity;
        long totalprice = bookQuantity * bookData.getPrice();
        int cartId = 0;
        Cart cart = new Cart();
        if (cartRepo.findById(cartDTO.userId).isPresent()){
            bookQuantity = bookQuantity+1;
            cartId= cartRepo.findCartsByUserId(cartDTO.userId).getCartId();
            cart = new Cart(cartId,userData, bookData,bookQuantity,totalprice);

        }else {
            cart = new Cart(userData, bookData,bookQuantity,totalprice);
        }
        return cartRepo.save(cart);
    }

    public Cart getCartItems(String token) {
        int id=tokenUtility.decodeToken(token);
        return cartRepo.findCartsByUserId(id);
//        return cartRepo.findCartsByUserId(id);
    }

    public Object removeById(Integer id, String token) {
        UserRegistrationModule userRegistrationModule = iUserRegistration.getUserById(token);
        Optional<Cart> cart = cartRepo.findById(id);
        if (cart.isPresent() && userRegistrationModule != null){
            cartRepo.delete(cart.get());
            return "Record is deleted with id ";
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }

}
