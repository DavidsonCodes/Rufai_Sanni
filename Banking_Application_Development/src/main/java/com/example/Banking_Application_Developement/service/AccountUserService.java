package com.example.Banking_Application_Developement.service;

import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.repository.AccountUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
//@Component
public class AccountUserService {
//    @Autowired
    private final AccountUserRepository accountUserRepository;

    public AccountUserService(AccountUserRepository accountUserRepository) {
        this.accountUserRepository = accountUserRepository;
    }

    // Get all Account users
    public ResponseEntity<List<AccountUser>> getAllAccountUsers(){
        return new ResponseEntity<>(accountUserRepository.findAll(), HttpStatus.OK);
    }

    // Get Account user by id
    public ResponseEntity<AccountUser> getAccountUserById(int id){
        return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.OK);
    }
    // Get Account user by email
    public ResponseEntity<AccountUser> getAccountUserByEmail(String email){
        return new ResponseEntity<>(accountUserRepository.findByEmail(email), HttpStatus.OK);
    }
    // Get Account user by PhoneNumber
    public ResponseEntity<AccountUser> getAccountUserByPhoneNumber(String phoneNumber){
        return new ResponseEntity<>(accountUserRepository.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    // Create New Account User
    public ResponseEntity<AccountUser> addNewAccountUser(AccountUser accountUser){
        return new ResponseEntity<>(accountUserRepository.save(accountUser), HttpStatus.CREATED);
    }

    // Update an existing Account User
    public ResponseEntity<AccountUser> updateAccountUser(int id, AccountUser accountUser){
        AccountUser accountUserFromDb = accountUserRepository.findById(id).get();

        accountUserFromDb.setFirstName(accountUser.getFirstName());
        accountUserFromDb.setMiddleName(accountUser.getMiddleName());
        accountUserFromDb.setLastName(accountUser.getLastName());
        accountUserFromDb.setEmail(accountUser.getEmail());
        accountUserFromDb.setPassword(accountUser.getPassword());
        accountUserFromDb.setPhoneNumber(accountUser.getPhoneNumber());
        return new ResponseEntity<>(accountUserRepository.save(accountUser), HttpStatus.CREATED);
    }

    // Update a particular filed of an existing Account User
    public ResponseEntity<AccountUser> updateOneFieldOfAccountUser(int id, Map<String, Object> accountUser){
        Optional<AccountUser> accountUserFromDb = accountUserRepository.findById(id);
        if(accountUserFromDb.isPresent()){
            AccountUser accountToUpdate = accountUserFromDb.get();

            if(accountUser.containsKey("firstName")){
                accountToUpdate.setFirstName((String) accountUser.get("firstName"));
            }
            if(accountUser.containsKey("lastName")){
                accountToUpdate.setFirstName((String) accountUser.get("lastName"));
            }
            if(accountUser.containsKey("email")){
                accountToUpdate.setFirstName((String) accountUser.get("email"));
            }
            if(accountUser.containsKey("password")){
                accountToUpdate.setFirstName((String) accountUser.get("password"));
            }
            if(accountUser.containsKey("phoneNumber")){
                accountToUpdate.setFirstName((String) accountUser.get("phoneNumber"));
            }
            AccountUser updatedAccountUser = accountUserRepository.save(accountToUpdate);

            return new ResponseEntity<>(updatedAccountUser, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Remove Account User
    public ResponseEntity<AccountUser> removeAccountUser(int id){
        return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.OK);
//        return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.NO_CONTENT);
    }

}
