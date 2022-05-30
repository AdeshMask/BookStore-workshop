package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.module.CartModule;

public interface ICartService {
//    Object saveBooking(CartModule cartModule);

    Object addCart(CartDTO cartDTO);
}
