package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.reository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    BookRepo bookRepo;
    @Override
    public Object addBook(BookModule newBookModule) {
        BookModule bookModule = new BookModule(newBookModule);
        bookRepo.save(bookModule);
        return bookModule;
    }

    @Override
    public BookModule getBookById(int bookId){
        return bookRepo.findById(bookId).orElseThrow(() -> new BookStoreExceptionHandler("Book  with id " + bookId + " does not exist in database..!"));

    }

    @Override
    public Object searchAll() {
        return bookRepo.findAll();
    }

    @Override
    public Object update(Integer id, BookDTO bookDTO) {
        if (bookRepo.findById(id).isPresent()) {
            BookModule newBookModule = new BookModule(id, bookDTO);
            BookModule search = bookRepo.save(newBookModule);
            return "Done " + search;
        }
        throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public Object removeById(Integer id) {
        Optional<BookModule> newBookModule = bookRepo.findById(id);
        if (newBookModule.isPresent()){
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
}

//
//<dependency>
//<groupId>org.springframework.boot</groupId>
//<artifactId>spring-boot-starter-mail</artifactId>
//</dependency>


//audspbiqadsxhodf
//youtube.com/watch?v=ugIUObNHZdo