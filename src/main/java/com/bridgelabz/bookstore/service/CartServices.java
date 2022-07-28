package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.Cart;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.BookRepo;
import com.bridgelabz.bookstore.reository.CartRepo;
import com.bridgelabz.bookstore.reository.IUsrRegistrationRepo;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    BookRepo bookRepo;
    @Autowired
    IUsrRegistrationRepo userRepositoration;
    @Autowired
    TokenUtility tokenUtility;
    @Override
    public Object addToCart(CartDTO cartDTO, String token) {
        int id = tokenUtility.decodeToken(token);
        Optional<UserRegistrationModule> userData = userRepositoration.findById(id);
        Optional<BookModule> bookData = iBookService.getBookById(cartDTO.getBookId());
        if (userData.isPresent() && bookData.isPresent()) {
            if (bookData.get().bookQuantity >= cartDTO.getQuantity() && cartDTO.getQuantity() > 0) {
                Cart cart = cartRepo.findCartsByUserIdAndBookId(cartDTO.getBookId(), userData.get().id);
                if (cart != null) {
                    Cart cartDetails = updateQuantity(token, cartDTO, cart.getCartId());
                    return cartDetails;
                } else {
                    double totalPrice = cartDTO.getQuantity() * bookData.get().getPrice();
                    Cart cartDetails = new Cart(userData.get(), bookData.get(), cartDTO.getQuantity(), totalPrice);
                    return cartRepo.save(cartDetails);
                }
            }throw (new BookStoreExceptionHandler("Book Out Of Stock"));
        }throw (new BookStoreExceptionHandler("Record not Found"));
    }

    public List<Cart> getCartItems(String token) {
        int id=tokenUtility.decodeToken(token);
        return  cartRepo.findCartsByUserId(id);
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

    @Override
    public String emptyCart() {
        cartRepo.deleteAll();
        return "All Cart Item Deleted";
    }

    public String deleteByUserId(String token) {
        int id = tokenUtility.decodeToken(token);
        cartRepo.deleteByUserId(id);
        return "Record Delete Successful";
    }

    @Override
    public Cart updateQuantity(String token, CartDTO cartDTO, int id) {
        UserRegistrationModule userData = iUserRegistration.getUserById((token));
        if (cartRepo.findById(id).isPresent() && userData != null) {
            Cart cart = cartRepo.findById(id).get();
            cart.setQuantity(cartDTO.quantity);
            cart.setTotalPrice(cart.getQuantity() * cart.getBook().getPrice());
            return cartRepo.save(cart);
        } else throw new BookStoreExceptionHandler("No book found with the given id or you are not an admin user.");
    }
}
