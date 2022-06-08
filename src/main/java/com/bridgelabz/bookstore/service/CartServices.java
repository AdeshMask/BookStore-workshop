package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.Cart;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServices implements ICartService{

    @Autowired
    IUserRegistration iUserRegistration;
    @Autowired
    IBookService iBookService;

    @Autowired
    EmailService emailService;
    @Autowired
    CartRepo cartRepo;


    @Override
    public Cart addToCart(CartDTO cartDTO, String token) {
        ArrayList<BookModule> bookList = new ArrayList<>();
        UserRegistrationModule userData = iUserRegistration.getUserById(token);
        List<Integer> bookIdList = cartDTO.bookId;
        List<Integer> quantity = cartDTO.quantity;
        for (int i = 0; i < bookIdList.size(); i++) {
            if (quantity.get(i) <= iBookService.getBookById(bookIdList.get(i)).getBookQuantity()) {
                bookList.add(iBookService.getBookById(bookIdList.get(i)));
                iBookService.updateQuantityById(bookIdList.get(i), iBookService.getBookById(bookIdList.get(i)).getBookQuantity() - (quantity.get(i)), token);
            } else
                throw new BookStoreExceptionHandler("Please select a small quantity to order as stocks are limited: Current stock for book id: "
                        + bookIdList.get(i) + " is " + iBookService.getBookById(bookIdList.get(i)).getBookQuantity() + ".");
        }
        List<String> nameList = bookList.stream().map(BookModule::getBookName).toList();
        Cart cart = new Cart(userData,cartDTO.bookId ,quantity);
        return cartRepo.save(cart);
    }

    public List<Cart> getCartItems(String token) {
        return cartRepo.findAll();
    }

    public Object removeById(Integer id, String token) {
        UserRegistrationModule userRegistrationModule = iUserRegistration.getUserById(token);
        Optional<Cart> cart = cartRepo.findById(id);
        if (cart.isPresent() && userRegistrationModule != null){
            cartRepo.delete(cart.get());
            return "Record is deleted with id ";
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }
}
