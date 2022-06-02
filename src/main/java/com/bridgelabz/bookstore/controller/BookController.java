package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.module.BookModule;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService iBookService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello Adesh";
    }

    /*--------------------Post Operation-------------------*/
    @PostMapping("/add")
        public ResponseEntity<RespnseDTO> addBook(@RequestBody BookDTO bookDTO){
            BookModule newBookModule = new BookModule(bookDTO);
            RespnseDTO responseDTO = new RespnseDTO("Add record  Success", iBookService.addBook(newBookModule));
            return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
        }

    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<RespnseDTO> getAddressById(@PathVariable Integer id) {
        RespnseDTO responseDTO = new RespnseDTO("Record found successfully", iBookService.getBookById(id));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.CREATED);
    }
    //----------------------------------------get-by-Book---------------------------
    @GetMapping("/get-book/{bookName}")
    public ResponseEntity<RespnseDTO> getBookByName(@PathVariable("bookName") String bookName){
        RespnseDTO responseDTO = new RespnseDTO("Get Call for Name successful",iBookService.findBookByName(bookName));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<RespnseDTO> getAddress(){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iBookService.searchAll());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
    //-----------------------------------------Update-------------------------------
    @PutMapping("/edit/{id}")
    public ResponseEntity<RespnseDTO> editData(@PathVariable Integer id,@RequestBody BookDTO bookDTO) {
        RespnseDTO responseDTO = new RespnseDTO("Successfully updated",iBookService.update(id,bookDTO));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    //---------------------------------------Delete---------------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RespnseDTO> deleteAddress(@PathVariable Integer id){
        RespnseDTO responseDTO = new RespnseDTO("Delete Operation Successful", iBookService.removeById(id));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
    //---------------------------------------Sorting---------------------------------
    @GetMapping(value = "/sort")
    public ResponseEntity<RespnseDTO> sortAscByBookName(){
        RespnseDTO responseDTO = new RespnseDTO("Sorting The records by Name", iBookService.sortByBookName());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
    @GetMapping(value = "/sortprice")
    public ResponseEntity<RespnseDTO> sortAscByBookPrice(){
        RespnseDTO responseDTO = new RespnseDTO("Sorting The records by Name", iBookService.sortAscByBookPrice());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
}
