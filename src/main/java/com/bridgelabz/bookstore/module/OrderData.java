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

    @ElementCollection
    @CollectionTable
    public List<Integer> bookId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule userId;

    public String address;

    @ElementCollection
    public List<Integer> quantity;
    boolean cancle = true;
    public LocalDate orderDate= LocalDate.now();
    float totalPrice;

    public OrderData(UserRegistrationModule userData, List<Integer> bookId, String address, List<Integer> quantity,float totalPrice) {
        this.orderId = getOrderId();
        this.bookId = bookId;
        this.userId = userData;
        this.address = address;
        this.cancle = isCancle();
        this.orderDate = getOrderDate();
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
