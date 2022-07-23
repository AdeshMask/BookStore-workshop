package com.bridgelabz.bookstore.module;

import com.bridgelabz.bookstore.dto.WishListDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int wId;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UserRegistrationModule userId;
    @ManyToOne
    @JoinColumn(name = "bookId")
    public BookModule bookId;

    public WishList(UserRegistrationModule userRegistrationModule, BookModule bookService) {
        this.bookId = bookService;
        this.userId = userRegistrationModule;
    }
}
