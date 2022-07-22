package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.WishListDTO;
import com.bridgelabz.bookstore.module.WishList;

import java.util.List;

public interface IWishList {
    WishList addItem(WishListDTO wishListDTO, String token);

    String update(Integer id, WishListDTO wishListDTO);

    List<WishList> searchAll();

    List<WishList> getItemById(String token);
    String removeById(Integer id);
}
