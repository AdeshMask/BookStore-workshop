package com.bridgelabz.bookstore.module;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
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
    public BookModule bookId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule userId;

    public int quantity;
    public long totalPrice;


    public BookModule getBookId() {
        return bookId;
    }

    public void setBookId(BookModule bookId) {
        this.bookId = bookId;
    }

    public Cart(UserRegistrationModule userId, BookModule bookId, int quantity,long totalPrice){
        this.cartId = cartId;
        this.bookId = bookId;
        this.userId = userId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
    public Cart(int cartId,UserRegistrationModule userId, BookModule bookId, int quantity,long totalPrice){
        this.cartId = cartId;
        this.bookId = bookId;
        this.userId = userId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
