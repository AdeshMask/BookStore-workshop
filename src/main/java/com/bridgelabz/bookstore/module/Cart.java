package com.bridgelabz.bookstore.module;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    public int cartId;

    @ElementCollection
    @CollectionTable
    public List<Integer> bookId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule userId;

    @ElementCollection
    public List<Integer> quantity;

    public Cart(UserRegistrationModule userId, List<Integer> bookId, List<Integer> quantity){
        this.cartId = cartId;
        this.bookId = bookId;
        this.userId = userId;
        this.quantity = quantity;
    }
}
