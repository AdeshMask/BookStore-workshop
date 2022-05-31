package com.bridgelabz.bookstore.module;

import com.bridgelabz.bookstore.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModule {
    @Id
    @Column(name = "userID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String fullName;
    String mobileNumber;
    String userName;
    String password;
    String emailId;

    public UserRegistrationModule(UserDTO userDTO) {
        this.id = id;
        this.fullName = userDTO.fullName;
        this.mobileNumber = userDTO.mobileNumber;
        this.userName = userDTO.userName;
        this.password = userDTO.password;
        this.emailId = userDTO.emailId;
    }

    public UserRegistrationModule(UserRegistrationModule newUserRegistartionModule) {
        this.id = id;
        this.fullName = newUserRegistartionModule.fullName;
        this.mobileNumber = newUserRegistartionModule.mobileNumber;
        this.userName = newUserRegistartionModule.userName;
        this.password = newUserRegistartionModule.password;
        this.emailId = newUserRegistartionModule.emailId;
    }

    public UserRegistrationModule(Integer id, UserDTO userDTO) {
        this.id = id;
        this.fullName = userDTO.fullName;
        this.mobileNumber = userDTO.mobileNumber;
        this.userName = userDTO.userName;
        this.password = userDTO.password;
        this.emailId = userDTO.emailId;
    }
}
