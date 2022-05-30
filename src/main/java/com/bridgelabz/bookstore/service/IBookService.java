package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.module.BookModule;

import java.util.List;

public interface IBookService {
    Object addBook(BookModule newBookModule);

    Object searchById(Integer id);

    Object searchAll();

    Object update(Integer id, BookDTO bookDTO);

    Object removeById(Integer id);

    List<BookModule> findBookByName(String bookName);

    Object sortByBookName();
}
