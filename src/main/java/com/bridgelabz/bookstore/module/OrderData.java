package com.bridgelabz.bookstore.module;


import com.bridgelabz.bookstore.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    @Id
    @GeneratedValue
    public int orderId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    public BookModule bookId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule userId;

    public String address;
    boolean cancle = true;

    float totalPrice;

//    public OrderData(OrderDTO orderDTO) {
//        this.orderId = getOrderId();
//        this.bookId = orderDTO.getBookId();
//        this.userId = orderDTO.getUserId();
//    }

    public OrderData(UserRegistrationModule userData, BookModule book, String address, float totalPrice) {
        this.orderId = getOrderId();
        this.bookId = book;
        this.userId = userData;
        this.address = address;
        this.cancle = isCancle();
        this.totalPrice = totalPrice;
    }
    public OrderData(UserRegistrationModule userData, BookModule book, String address,boolean cancle) {
        this.orderId = getOrderId();
        this.bookId = book;
        this.userId = userData;
        this.address = address;
        this.cancle = cancle;
    }

}
