package com.bridgelabz.bookstore.module;

import com.bridgelabz.bookstore.dto.WishListDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue
    public int wId;
    public int userId;
    public int bookId;

    public WishList(WishList wishList) {
        this.bookId = wishList.getBookId();
        this.userId = wishList.getUserId();
        this.wId = wishList.wId;
    }

    public WishList(UserRegistrationModule userRegistrationModule, BookModule bookService) {
        this.bookId = bookService.getBookId();
        this.userId = userRegistrationModule.getId();
    }

    public WishList(WishListDTO wishListDTO) {
        this.bookId = wishListDTO.getBookId();
//        this.userId = wishListDTO.getUserId();
    }

    public WishList(Integer id, WishListDTO wishListDTO) {
        this.bookId = id;
//        this.userId = wishListDTO.getUserId();
    }
}
