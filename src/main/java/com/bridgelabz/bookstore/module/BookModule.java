package com.bridgelabz.bookstore.module;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookModule {
    @Id
    @Column(name = "bookId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int bookId;
    public String bookName;
    public int price;
    public String authorName;
    public int bookQuantity;

    public BookModule(BookDTO bookDTO) {
        this.bookId=bookId;
        this.bookName = bookDTO.bookName;
        this.price = bookDTO.price;
        this.authorName = bookDTO.authorName;
        this.bookQuantity = bookDTO.bookQuantity;
    }

    public BookModule(BookModule newBookModule) {
        this.bookId=bookId;
        this.bookName = newBookModule.bookName;
        this.price = newBookModule.price;
        this.authorName = newBookModule.authorName;
        this.bookQuantity = newBookModule.bookQuantity;
    }

    public BookModule(Integer id, BookDTO bookDTO) {
        this.bookId=bookId;
        this.bookName = bookDTO.bookName;
        this.price = bookDTO.price;
        this.authorName = bookDTO.authorName;
        this.bookQuantity = bookDTO.bookQuantity;
    }

    public BookModule(CartDTO cartDTO) {
    }
}
