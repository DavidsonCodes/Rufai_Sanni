package com.example.Banking_Application_Developement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "account_users_table")
//@Table(name="account_user", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "phone_number"}))
public class AccountUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_user_id")
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
    @Column(unique = true)
    private String username;
    @NotBlank(message = "This field cannot be blank")
    private String password;
    @NotBlank(message = "This field cannot be blank")
//    @Pattern(regexp = "^[0-9]+$", message = "Only numbers are allowed.")
    @Pattern(regexp = "[0-9]{11}")
//    @Size(min = 11, max = 11, message = "Field length must be exactly 11 characters")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public AccountUser() {
    }

    public AccountUser(String firstName, String middleName, String lastName, String username, String password, String phoneNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
