package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.CartModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartModule, Integer> {
//    @Query(value = "SELECT * FROM cart_module cm WHERE " +
//            "EXISTS (SELECT 1 FROM customer c WHERE bs.customer_id = c.id AND c.phone = :phone) " +
//            "AND EXISTS (SELECT 1 FROM books b WHERE b.id = bs.book_id AND b.author IN :authors)",
//            nativeQuery = true)
//    List<Booking> queryBy(@Param("phone") String phone,
//                          @Param("authors") List<String> authors);
//}
}
