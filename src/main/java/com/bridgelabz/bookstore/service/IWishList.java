package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.WishListDTO;
import com.bridgelabz.bookstore.module.WishList;

import java.util.List;

public interface IWishList {
    WishList addItem(WishListDTO wishListDTO);

    String update(Integer id, WishListDTO wishListDTO);

    List<WishList> searchAll();

    WishList getItemById(int userId);
    String removeById(Integer id);
}
