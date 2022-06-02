package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    public String address;

    public List<Integer> bookId;

    public int userId;

    public List<Integer> quantity;
}
