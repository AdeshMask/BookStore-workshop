package com.bridgelabz.bookstore.module;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartModule {

    @ManyToOne
    @JoinColumn(name = "book_module_book_id")
    BookModule bookModule;

    @Id
    @GeneratedValue
    public int cartId;

    @NotNull
    @JoinColumn(name = "user_id")
    public int userId;
    public int cartItems;
    public int price;
//
//    public CartModule(CartDTO cartDTO) {
//        this.bookModule=getBookModule();
//        this.userId = cartDTO.userId;
//        this.cartItems = cartDTO.cartItems;
//        this.price = cartDTO.price;
//    }

    public CartModule(CartDTO cartDTO) {
        this.userId = cartDTO.userId;
        this.cartItems = cartDTO.cartItems;
        this.price = cartDTO.price;
    }
}
