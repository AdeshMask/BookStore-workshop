package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    public String address;

    public BookModule bookId;

    public UserRegistrationModule userId;

}
