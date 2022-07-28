package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart , Integer> {

    @Query(value = "SELECT * FROM bookstore.cart where user_id = :userId", nativeQuery = true)
    List<Cart> findCartsByUserId(int userId);

    @Query(value = "delete from bookstore.cart where user_id = :userId",nativeQuery = true)
    void deleteByUserId(int userId);

    @Query(value = "select * from bookstore.cart where user_id = :id and book_id = :bookId",nativeQuery = true)
    Cart findCartsByUserIdAndBookId(int bookId, int id);

    @Query(value = "select book_id from bookstore.cart where user_id = :id",nativeQuery = true)
    List<Cart> findBookByUserId(int id);
}
