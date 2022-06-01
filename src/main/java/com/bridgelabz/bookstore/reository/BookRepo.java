package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.BookModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookModule, Integer> {
        @Query(value = "SELECT * FROM book_module e WHERE e.name = :bName", nativeQuery = true)
        List<BookModule> findBookByName(@Param("bName") String name);

//    @Query(value = "SELECT * FROM book_module e WHERE e.book_id = :bookId", nativeQuery = true)
//    List<BookModule> getBookById(@Param("bookId") String name);

    @Query("FROM BookModule ORDER BY bookName ASC")
    List<BookModule> sortByBookName();

    @Query("FROM BookModule ORDER BY price ASC")
    List<BookModule> sortAscByBookPrice();

    @Query(value = "SELECT * FROM book_module e WHERE e.book_id = :bookId", nativeQuery = true)
    BookModule getBookById(BookModule bookId);
}
