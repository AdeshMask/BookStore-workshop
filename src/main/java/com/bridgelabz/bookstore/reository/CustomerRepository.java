package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer> {
}
