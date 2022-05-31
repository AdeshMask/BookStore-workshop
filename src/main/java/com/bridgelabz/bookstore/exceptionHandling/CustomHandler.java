package com.bridgelabz.bookstore.exceptionHandling;

import com.bridgelabz.bookstore.dto.RespnseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespnseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream().map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        RespnseDTO responseDTO = new RespnseDTO("Exception while parsing Rest request",errorMessage);
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookStoreExceptionHandler.class)
    public ResponseEntity<RespnseDTO> handleMethodArgumentNotValidException(BookStoreExceptionHandler exception){
        RespnseDTO responseDTO = new RespnseDTO("Exception while parsing Rest request",exception.getMessage());
        return new ResponseEntity<RespnseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
