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
    public CartModule addCart(CartDTO cartDTO) {
        UserRegistrationModule user = iUserRegistration.getUserById(tokenUtility.createToken(cartDTO.getUserId()));
        BookModule book = iBookService.getBookById(cartDTO.getBookId());
        CartModule cart = new CartModule(user , book, cartDTO.quantity);
        return cartRepo.save(cart);
    }

    @Override
    public Object getCartById(Integer id) {
        return cartRepo.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("Book  with id " + id + " does not exist in database..!"));

    }

    @Override
    public Object getAllCartItems() {
        return cartRepo.findAll();
    }

    @Override
    public Object removeById(Integer id) {
        Optional<CartModule> cartModule = cartRepo.findById(id);
        if (cartModule.isPresent()){
            cartRepo.delete(cartModule.get());
            return "Record is deleted with id ";
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public Object update(Integer id, int quantity) {
        if (cartRepo.findById(id).isPresent()) {
            CartModule cartModule = new CartModule(id, quantity);
            CartModule search = cartRepo.save(cartModule);
            return "Done " + search;
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }
}
