package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepo extends JpaRepository<WishList,Integer> {
    @Query(value = "Select * from bookstore.wish_list where user_id = :userId", nativeQuery = true)
    List<WishList> findWishlistById(int userId);
}
