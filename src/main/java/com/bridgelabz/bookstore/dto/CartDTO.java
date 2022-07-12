package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;


@RequiredArgsConstructor
public class CartDTO {

    public int bookId;

    public int userId;
    public int quantity;
}
