package com.bridgelabz.bookstore.module;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int orderId;

    @OneToOne
    @JoinColumn(name = "custId")
    public CustomerDetails custId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToMany(fetch = FetchType.LAZY)
    private List<Cart> cart;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule user;

    boolean cancel = true;
    public LocalDate orderDate= LocalDate.now();


    public OrderData(UserRegistrationModule userData, List<Cart> cart, CustomerDetails customerDetails) {
        this.user = userData;
        this.cart = cart;
        this.custId = customerDetails;
        this.orderDate = LocalDate.now();
    }
}
