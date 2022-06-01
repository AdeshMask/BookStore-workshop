package com.bridgelabz.bookstore.module;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class CartModule {

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    public BookModule bookId;

    @Id
    @GeneratedValue
    public int cartId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule userId;

    public int quantity;

    public CartModule(UserRegistrationModule userRegistrationModule, BookModule bookModule, int quantity) {
        this.userId = userRegistrationModule;
        this.bookId = bookModule;
        this.quantity = quantity;
    }
    public CartModule() {

    }
    public CartModule(Integer id, int quantity) {
        this.cartId=id;
        this.quantity = quantity;
    }
}
