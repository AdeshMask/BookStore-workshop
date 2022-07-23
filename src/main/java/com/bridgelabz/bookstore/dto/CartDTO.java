package com.bridgelabz.bookstore.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class CartDTO {
    public int bookId;
    public int quantity;
    double totalPrice;
}
