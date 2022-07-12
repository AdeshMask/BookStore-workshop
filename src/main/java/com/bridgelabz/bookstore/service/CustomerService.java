package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CustomerDTO;
import com.bridgelabz.bookstore.exceptionHandling.BookStoreExceptionHandler;
import com.bridgelabz.bookstore.module.CustomerDetails;
import com.bridgelabz.bookstore.reository.CustomerRepository;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TokenUtility tokenUtility;

    @Override
    public Object addCustomerDetails(CustomerDTO customerDTO) {
        CustomerDetails cusomer = new CustomerDetails(customerDTO);
        return customerRepository.save(cusomer);
    }

    @Override
    public Object getUserById(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("Customer with id " + id + " does not exist in database..!"));

    }
}
