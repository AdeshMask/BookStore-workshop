package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderData,Integer> {
}
