package com.example.Banking_Application_Developement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;


@Entity
//@Table(name="account_user", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "phone_number"}))
public class AccountUser extends RepresentationModel<AccountUser> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Length(min=2, max=40)
    @NotBlank(message = "This field cannot be blank")
    private String firstName;

    @Length(min=2, max=40)
    private String middleName;

    @Length(min=2, max=40)
    @NotBlank(message = "This field cannot be blank")
    private String lastName;

    @Email(message = "Provide a valid email address")
    @NotBlank(message = "This field cannot be blank")
    private String username;
    @NotBlank(message = "This field cannot be blank")
    @Length(min=8, max=20)
    private String password;
    @NotBlank(message = "This field cannot be blank")
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return username;
    }

    public void setEmail(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
