package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepo extends JpaRepository<WishList,Integer> {
}
