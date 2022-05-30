package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.CartModule;
import com.bridgelabz.bookstore.reository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{

    @Autowired
    CartRepo cartRepo;
//
//    @Override
//    public Object saveBooking(CartModule cartModule) {
//        return cartRepo.findAll();
//    }

    @Override
    public Object addCart(CartDTO cartDTO) {
        CartModule cartModule = new CartModule(cartDTO);
        cartRepo.save(cartModule);
        return cartModule;
    }
}
