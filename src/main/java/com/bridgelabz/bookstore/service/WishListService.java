package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.WishListDTO;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.module.WishList;
import com.bridgelabz.bookstore.reository.BookRepo;
import com.bridgelabz.bookstore.reository.IUsrRegistrationRepo;
import com.bridgelabz.bookstore.reository.WishListRepo;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService implements IWishList {
    @Autowired
    WishListRepo wishListRepo;
    @Autowired
    IUserRegistration iUserRegistration;
    @Autowired
    IUsrRegistrationRepo iUsrRegistrationRepo;
    @Autowired
    IBookService iBookService;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    TokenUtility tokenUtility;
    @Override
    public WishList addItem(WishListDTO wishListDTO, String token) {
        int id = tokenUtility.decodeToken(token);
        UserRegistrationModule userRegistrationModule = iUserRegistration.getUserId(id);
        Optional<BookModule> bookService = iBookService.getBookById(wishListDTO.getBookId());
        WishList wishList = new WishList(userRegistrationModule, bookService.get());
        return wishListRepo.save(wishList);

    }

//    @Override
//    public String update(Integer id, WishListDTO wishListDTO) {
//        if (wishListRepo.findById(id).isPresent()) {
//            WishList wishList = new WishList(id, wishListDTO);
//            WishList search = wishListRepo.save(wishList);
//            return "Done " + search;
//        }
//        throw (new BookStoreExceptionHandler("Record not Found"));
//    }

    @Override
    public List<WishList> searchAll() {
        return wishListRepo.findAll();
    }

    @Override
    public List<WishList> getItemById(String token) {
        int userId = tokenUtility.decodeToken(token);
        List<WishList> wishList = wishListRepo.findWishlistById(userId);
        return wishList;
    }

    @Override
    public String removeById(Integer id, String token) {
        int userId = tokenUtility.decodeToken(token);
        UserRegistrationModule user = iUserRegistration.getUserId(userId);
        if (user != null) {
            Optional<WishList> wishList = wishListRepo.findById(id);
            if (wishList.isPresent()) {
                wishListRepo.delete(wishList.get());
                return "Record is deleted with id ";
            }
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }
}
