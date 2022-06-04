package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.controller.UserRegistration;
import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.CartModule;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.CartRepo;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    CartRepo cartRepo;
    @Autowired
    IUserRegistration iUserRegistration;
    @Autowired
    IBookService iBookService;

    @Autowired
    TokenUtility tokenUtility;
    @Override
    public CartModule addCart(CartDTO cartDTO,String token) {
        UserRegistrationModule user = iUserRegistration.getUserById(token);
        BookModule book = iBookService.getBookById(cartDTO.getBookId());
        CartModule cart = new CartModule(user , book, cartDTO.quantity);
        return cartRepo.save(cart);
    }

    @Override
    public Object getCartById(String token) {
        int id=tokenUtility.decodeToken(token);
        return cartRepo.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("Book  with id " + id + " does not exist in database..!"));

    }

    @Override
    public Object getAllCartItems() {
        return cartRepo.findAll();
    }

    @Override
    public Object removeById(Integer id,String token) {
        UserRegistrationModule userRegistrationModule = iUserRegistration.getUserById(token);
        Optional<CartModule> cartModule = cartRepo.findById(id);
        if (cartModule.isPresent() && userRegistrationModule != null){
            cartRepo.delete(cartModule.get());
            return "Record is deleted with id ";
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public Object update(Integer id, int quantity,String token) {
        UserRegistrationModule userRegistrationModule = iUserRegistration.getUserById(token);
        if (cartRepo.findById(id).isPresent() && userRegistrationModule != null) {
            CartModule cartModule = new CartModule(id, quantity);
            CartModule search = cartRepo.save(cartModule);
            return "Done " + search;
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }
}
