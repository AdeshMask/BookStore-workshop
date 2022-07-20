package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CustomerDTO;
import com.bridgelabz.bookstore.module.CustomerDetails;

public interface ICustomerService {

    public Object addCustomerDetails(CustomerDTO customerDTO);

    CustomerDetails getUserById(int id);
}
