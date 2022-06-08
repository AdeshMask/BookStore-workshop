package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart , Integer> {
}
