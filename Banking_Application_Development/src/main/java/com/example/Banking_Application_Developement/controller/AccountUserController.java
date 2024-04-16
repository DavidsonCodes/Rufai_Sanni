package com.example.Banking_Application_Developement.controller;


import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.service.AccountUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class AccountUserController {
//    @Autowired
    private final AccountUserService accountUserService;

    public AccountUserController(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }
// Create endpoints

    // Get all Account Users
    @GetMapping("/allAccountUsers")
    public ResponseEntity<List<AccountUser>> getAllAccountUsers(){
        return accountUserService.getAllAccountUsers();
    }

    // Get Account User By Id
    @GetMapping("/accountUser/id/{id}")
    public ResponseEntity<AccountUser> getAccountUserById(@PathVariable int id){
        return accountUserService.getAccountUserById(id);
    }

    // Get Account User By Phone Number
    @GetMapping("/accountUser/phoneNumber/{phoneNumber}")
    public ResponseEntity<AccountUser> getAccountUserByPhone(@PathVariable String phoneNumber){
        return accountUserService.getAccountUserByPhoneNumber(phoneNumber);
    }
    // Get Account User By Email
    @GetMapping("/accountUser/email/{email}")
    public ResponseEntity<AccountUser> getAccountUserByEmail(@PathVariable String email){
        return accountUserService.getAccountUserByEmail(email);
    }

    // Add new Account User
    @PostMapping("/addUser")
    public ResponseEntity<AccountUser> addNewAccountUser(@RequestBody @Valid AccountUser accountUser){
        return accountUserService.addNewAccountUser(accountUser);
    }

    // Update Account User
    @PutMapping("/updateUser/id/{id}")
    public ResponseEntity<AccountUser> updateAccountUser(@PathVariable int id, @RequestBody AccountUser accountUser){
        return accountUserService.updateAccountUser(id, accountUser);
    }

    // Update an existing Account user field
    @PatchMapping("/patchUpdateUser/{id}")
    public ResponseEntity<AccountUser> updateOneFieldOfAccountUser(@PathVariable int id, @RequestBody  Map<String, Object> accountUser){
        return accountUserService.updateOneFieldOfAccountUser(id, accountUser);
    }

    // Remove an existing user
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<AccountUser> deleteAccountUser(@PathVariable int id){
        return accountUserService.removeAccountUser(id);
    }

}
