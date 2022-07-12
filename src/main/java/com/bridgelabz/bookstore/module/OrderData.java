package com.bridgelabz.bookstore.module;


import com.bridgelabz.bookstore.dto.OrderDTO;
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
    @GeneratedValue
    public int orderId;

    @ManyToOne
    @JoinColumn(name = "cartId")
    public Cart cartId;

    @ManyToOne
    @JoinColumn(name = "custId")
    public CustomerDetails custId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule userId;

    boolean cancle = true;
    public LocalDate orderDate= LocalDate.now();

    public OrderData(UserRegistrationModule userId, Cart cartId, Object custId) {
        this.orderId=getOrderId();
        this.userId=userId;
        this.custId=getCustId();
        this.cartId=getCartId();

    }


//    public OrderData(UserRegistrationModule userData, BookModule bookId, String address, int quantity,float totalPrice) {
//        this.orderId = getOrderId();
//        this.ca = bookId;
//        this.userId = userData;
//        this.address = address;
//        this.cancle = isCancle();
//        this.orderDate = getOrderDate();
//        this.quantity = quantity;
//        this.totalPrice = totalPrice;
//    }
}
