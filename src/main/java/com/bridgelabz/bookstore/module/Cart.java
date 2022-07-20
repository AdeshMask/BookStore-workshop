package com.bridgelabz.bookstore.module;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue
    public int cartId;
    @ManyToOne
    @JoinColumn(name = "bookId")
    public BookModule book;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule user;
    public int quantity;
    public long totalPrice;
    public Cart(UserRegistrationModule userId, BookModule bookId, int quantity,long totalPrice){
        this.cartId = cartId;
        this.book = bookId;
        this.user = userId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
