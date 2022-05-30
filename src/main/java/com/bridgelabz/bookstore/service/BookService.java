package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.reository.BookRepo;
import net.bytebuddy.TypeCache;
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
    public Object searchById(Integer id) {
        if (bookRepo.findById(id).isPresent()){
            return bookRepo.findById(id);
        }
        return null;
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
        return null;
    }

    @Override
    public Object removeById(Integer id) {
        Optional<BookModule> newBookModule = bookRepo.findById(id);
        if (newBookModule.isPresent()){
            bookRepo.delete(newBookModule.get());
            return "Record is deleted with id ";
        }
        return null;
    }
    public List<BookModule> findBookByName(String bookName) {
        List<BookModule> book = bookRepo.findBookByName(bookName);

        if (book.size() != 0) {
            return book;

        } return null;
    }

    @Override
    public Object sortByBookName() {
        return bookRepo.sortByBookName();
    }
}
