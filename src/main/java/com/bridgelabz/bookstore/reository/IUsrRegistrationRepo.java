package com.bridgelabz.bookstore.reository;

import com.bridgelabz.bookstore.module.UserRegistrationModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUsrRegistrationRepo extends JpaRepository<UserRegistrationModule, Integer> {
//    @Query(value = "select * from userregistration where email_Id= :emailId", nativeQuery = true)
//    Optional<UserRegistrationModule> findByEmailId(String emailId);

    Optional<UserRegistrationModule> findByEmailIdAndPassword(String email_Id, String password);
}
