package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.module.BookModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    BookModule bookModule;


    public int userId;
    public int cartItems;
    public int price;
}
