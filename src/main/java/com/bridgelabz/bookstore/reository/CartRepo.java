package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart , Integer> {

    @Query(value = "SELECT * FROM cart where user_id = :userId", nativeQuery = true)
    Cart findCartsByUserId(int userId);

}
