package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.UserRegistrationModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsrRegistrationRepo extends JpaRepository<UserRegistrationModule, Integer> {
}
