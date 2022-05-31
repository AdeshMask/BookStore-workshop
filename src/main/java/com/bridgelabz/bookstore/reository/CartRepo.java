package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.CartModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartModule, Integer> {

}
