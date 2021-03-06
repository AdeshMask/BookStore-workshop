package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.RespnseDTO;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*", origins = "*")
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
        public ResponseEntity<RespnseDTO> addBook(@RequestBody BookDTO bookDTO,String token){
            RespnseDTO responseDTO = new RespnseDTO("Add record  Success", iBookService.addBook(bookDTO,token));
            return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.CREATED);
        }

    //----------------------------------------get-by-Id---------------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<RespnseDTO> getBookById(@PathVariable Integer id) {
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
    public ResponseEntity<RespnseDTO> getAllBooks(){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iBookService.searchAll());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-low")
    public ResponseEntity<RespnseDTO> getByLowerPrice(){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iBookService.getByLowerPrice());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    //----------------------------------------Get-all------------------------------
    @GetMapping("/get-high")
    public ResponseEntity<RespnseDTO> getByHigherPrice(){
        RespnseDTO responseDTO = new RespnseDTO("Getting all the record..", iBookService.getByHigherPrice());
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
    //-----------------------------------------Update-------------------------------
    @PutMapping("/edit/{id}")
    public ResponseEntity<RespnseDTO> editData(@PathVariable Integer id,@RequestBody BookDTO bookDTO, String token) {
        RespnseDTO responseDTO = new RespnseDTO("Successfully updated",iBookService.update(id,bookDTO,token));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }

    //---------------------------------------Delete---------------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RespnseDTO> deleteBook(@PathVariable Integer id,@RequestParam String token){
        RespnseDTO responseDTO = new RespnseDTO("Delete Operation Successful", iBookService.removeById(id,token));
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
    //---------------------------------------Quantity Update---------------------------------
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<RespnseDTO> updateQuantityById(@PathVariable int id, @RequestBody BookDTO bookDTO,@RequestParam String token){
        RespnseDTO responseDTO = new RespnseDTO("Quantity Update Successful", iBookService.updateQuantityById(id,bookDTO,token));
        return new ResponseEntity<RespnseDTO>(responseDTO,HttpStatus.OK);
    }
}
