package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;


@RequiredArgsConstructor
public class CartDTO {
    @ElementCollection
    @CollectionTable
    public List<Integer> bookId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule userId;

    @ElementCollection
    public List<Integer> quantity;
}
