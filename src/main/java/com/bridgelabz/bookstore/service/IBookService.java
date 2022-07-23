package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.module.BookModule;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Object addBook(BookDTO bookDTO , String token);

    Optional<BookModule> getBookById(int bookId);

    Object searchAll();

    Object update(Integer id, BookDTO bookDTO,String token);

    Object removeById(Integer id, String token);

    List<BookModule> findBookByName(String bookName);

    Object sortByBookName();

    Object sortAscByBookPrice();

    public BookModule updateQuantityById(int id, BookDTO quantity, String token);

    Object getByLowerPrice();

    Object getByHigherPrice();
}
