package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.module.UserRegistrationModule;
import com.bridgelabz.bookstore.reository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    BookRepo bookRepo;
    @Autowired
    IUserRegistration iUserRegistration;
    @Autowired
    EmailService emailService;

    @Override
    public Object addBook(BookDTO bookDTO,String token) {
        UserRegistrationModule userRegistrationModule = iUserRegistration.getUserById(token);
        BookModule bookModule = new BookModule(bookDTO);
        if (userRegistrationModule != null) {
            emailService.sendEmail(userRegistrationModule.getEmailId(), "Book Created successfully!!",
                    "User " + userRegistrationModule.getFullName() + " has added new book successfully " + bookModule + ".");
            return bookRepo.save(bookModule);
        } else throw new BookStoreExceptionHandler("Invalid token or not an Admin");
    }

    @Override
    public Optional<BookModule> getBookById(int bookId){
        return bookRepo.findById(bookId);
    }

    @Override
    public Object searchAll() {
        return bookRepo.findAll();
    }

    @Override
    public Object update(Integer id, BookDTO bookDTO, String token) {
        UserRegistrationModule userRegistrationModule = iUserRegistration.getUserById(token);
        if (bookRepo.findById(id).isPresent() && userRegistrationModule != null) {
            BookModule newBookModule = new BookModule(id, bookDTO);
            BookModule search = bookRepo.save(newBookModule);
            emailService.sendEmail(userRegistrationModule.getEmailId(), "Book updated successfully!!",
                    "User " + userRegistrationModule.getFullName() + " has updated book successfully " + search + ".");

            return "Done " + search;
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public Object removeById(Integer id, String token) {
        UserRegistrationModule userRegistrationModule = iUserRegistration.getUserById(token);
        Optional<BookModule> newBookModule = bookRepo.findById(id);
        if (newBookModule.isPresent() && userRegistrationModule != null){
            bookRepo.delete(newBookModule.get());
            return "Record is deleted with id ";
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }
    public List<BookModule> findBookByName(String bookName) {
        List<BookModule> book = bookRepo.findBookByName(bookName);

        if (book.size() != 0) {
            return book;

        } throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public Object sortByBookName() {
        return bookRepo.sortByBookName();
    }

    @Override
    public Object sortAscByBookPrice() {
        return bookRepo.sortAscByBookPrice();
    }

    public BookModule updateQuantityById(int id, int quantity, String token) throws BookStoreExceptionHandler {
        UserRegistrationModule userData = iUserRegistration.getUserById((token));
        if (bookRepo.findById(id).isPresent() && userData != null) {
            Optional<BookModule> book = this.getBookById(id);
            book.get().setBookQuantity(quantity);
            return null;
        } else throw new BookStoreExceptionHandler("No book found with the given id or you are not an admin user.");
    }

    @Override
    public Object getByLowerPrice() {
        return bookRepo.getByLowerPrice();
    }
    @Override
    public Object getByHigherPrice() {
        return bookRepo.getByHigherPrice();
    }
}