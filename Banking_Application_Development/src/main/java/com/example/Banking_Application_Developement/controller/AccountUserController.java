package com.example.Banking_Application_Developement.controller;


import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.model.DTO.LoginRequest;
import com.example.Banking_Application_Developement.model.DTO.LoginResponse;
import com.example.Banking_Application_Developement.service.AccountUserService;
import com.example.Banking_Application_Developement.service.MessageService;
import jakarta.mail.MessageContext;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("api/v1")
public class AccountUserController {
    @Autowired
    private AccountUserService accountUserService;
    @Autowired
    private MessageService messageService;

//    public AccountUserController(AccountUserService accountUserService, MessageService messageService) {
//        this.accountUserService = accountUserService;
//        this.messageService = messageService;
//    }
// Create endpoints

    // Get Reset password code
    public void getResetCode(String username) throws MessagingException {
        AccountUser accountUser = accountUserService.getAccountUserByUsername(username).getBody();
        StringBuilder randomCode = new StringBuilder();
        int count = 1;
        while(count <= 6){
            String x = String.valueOf(new Random().nextInt(10));
            randomCode.append(x);
            count++;
        }
        messageService.sendResetCode(accountUser.getUsername(), randomCode.toString());
    }

    // Get all Account Users
    @GetMapping("/allAccountUsers")
//    @PreAuthorize("hasRole('ADMIN')")
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
    public ResponseEntity<AccountUser> getAccountUserByUsername(@PathVariable String username){
        return accountUserService.getAccountUserByUsername(username);
    }

    // Add new Account User
    @PostMapping("/register")
    public ResponseEntity<AccountUser> addNewAccountUser(@RequestBody @Valid AccountUser accountUser) throws MessagingException {
        return accountUserService.addNewAccountUser(accountUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) throws MessagingException {
        return accountUserService.authenticate(loginRequest);
    }

    // Update Account User
    @PutMapping("/updateUser/id/{id}")
    public ResponseEntity<AccountUser> updateAccountUser(@PathVariable int id, @RequestBody AccountUser accountUser){
        return accountUserService.updateAccountUser(id, accountUser);
    }

    // Update an existing Account user field
//    @PatchMapping("/patchUpdateUser/{id}")
//    public ResponseEntity<AccountUser> updateOneFieldOfAccountUser(@PathVariable int id, @RequestBody  Map<String, Object> accountUser){
//        return accountUserService.updateOneFieldOfAccountUser(id, accountUser);
//    }

    // Remove an existing user
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<AccountUser> deleteAccountUser(@PathVariable int id){
        return accountUserService.removeAccountUser(id);
    }

}
