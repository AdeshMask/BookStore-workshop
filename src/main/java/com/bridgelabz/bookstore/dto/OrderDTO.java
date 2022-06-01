package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    public String address;

    public int bookId;

    public int userId;

}
