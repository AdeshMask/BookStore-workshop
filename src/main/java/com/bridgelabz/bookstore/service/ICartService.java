package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.module.Cart;
import java.util.List;

public interface ICartService {
    Cart addToCart(CartDTO cartDTO, String token);
    List<Cart> getCartItems(String token);
    Object removeById(Integer id, String token);
}
