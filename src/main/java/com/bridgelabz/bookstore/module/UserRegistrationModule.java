package com.bridgelabz.bookstore.module;

import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.util.TokenUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModule {


    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    String fullName;
    String mobileNumber;
    String userName;
    String password;
    String emailId;
    String token;

    public UserRegistrationModule(UserDTO userDTO) {
        this.id = id;
        this.fullName = userDTO.fullName;
        this.mobileNumber = userDTO.mobileNumber;
        this.userName = userDTO.userName;
        this.password = userDTO.password;
        this.emailId = userDTO.emailId;
//        this.token = userDTO.getToken();
    }

    public UserRegistrationModule(UserRegistrationModule newUserRegistartionModule) {
        this.id = id;
        this.fullName = newUserRegistartionModule.fullName;
        this.mobileNumber = newUserRegistartionModule.mobileNumber;
        this.userName = newUserRegistartionModule.userName;
        this.password = newUserRegistartionModule.password;
        this.emailId = newUserRegistartionModule.emailId;
//        this.token = newUserRegistartionModule.token;
    }

    public UserRegistrationModule(Integer id, UserDTO userDTO) {
        this.id = id;
        this.fullName = userDTO.fullName;
        this.mobileNumber = userDTO.mobileNumber;
        this.userName = userDTO.userName;
        this.password = userDTO.password;
        this.emailId = userDTO.emailId;
//        this.token = userDTO.token;
    }
}