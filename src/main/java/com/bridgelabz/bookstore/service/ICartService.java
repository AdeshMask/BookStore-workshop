package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.module.CartModule;

public interface ICartService {
//    Object saveBooking(CartModule cartModule);

    CartModule addCart(CartDTO cartDTO,String token);

    Object getCartById(String token);

    Object getAllCartItems();

    Object removeById(Integer id,String token);

    Object update(Integer id, int quantity,String token);
}
