package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CustomerDTO;

public interface ICustomerService {

    public Object addCustomerDetails(CustomerDTO customerDTO);

    Object getUserById(int id);
}
