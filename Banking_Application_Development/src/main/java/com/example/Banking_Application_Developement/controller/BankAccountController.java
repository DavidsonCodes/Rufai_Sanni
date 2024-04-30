package com.example.Banking_Application_Developement.controller;


import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.model.BankAccount;
import com.example.Banking_Application_Developement.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bankAccounts")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("")
    public ResponseEntity<List<BankAccount>>getAllBankAccounts(){
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount>getBankAccountById(@PathVariable int id){
        return bankAccountService.getBankAccountById(id);
    }

    @GetMapping("")
    public ResponseEntity<BankAccount>getBankAccountByAccountUser( AccountUser accountUser){
        return bankAccountService.getBankAccountByAccountUser(accountUser);
    }

    @PostMapping("")
    public ResponseEntity<BankAccount>createBankAccount(@RequestBody AccountUser accountUser){
        return bankAccountService.createBankAccount(accountUser);
    }

    @PostMapping("openingBalance")
    public ResponseEntity<BankAccount>createBankAccount(@RequestBody AccountUser accountUser, @RequestParam double openingBal){
        return bankAccountService.createBankAccount(accountUser, openingBal);
    }
}
