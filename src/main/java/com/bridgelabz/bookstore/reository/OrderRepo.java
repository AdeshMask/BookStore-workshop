package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderData,Integer> {


    @Query(value = "select * from bookstore.order_data where user_id= :userId", nativeQuery = true)
    OrderData findByIserId(int userId);

}
