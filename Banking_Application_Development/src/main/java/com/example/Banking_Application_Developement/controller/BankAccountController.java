package com.example.Banking_Application_Developement.controller;


import com.example.Banking_Application_Developement.exception.TransactionException;
import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.model.BankAccount;
import com.example.Banking_Application_Developement.model.OperationRequest;
import com.example.Banking_Application_Developement.service.BankAccountService;
import com.example.Banking_Application_Developement.service.BankingOperationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {
//    @Autowired
//    private BankAccountService bankAccountService;
    @Autowired
    private BankingOperationService operationService;

//    @GetMapping("/allBankAccounts")
//    public ResponseEntity<List<BankAccount>>getAllBankAccounts(){
//        return bankAccountService.getAllBankAccounts();
//    }
//
//    @GetMapping("/bankAccount/{id}")
//    public ResponseEntity<BankAccount>getBankAccountById(@PathVariable int id){
//        return bankAccountService.getBankAccountById(id);
//    }
//
//    @GetMapping("/bankAccountUser")
//    public ResponseEntity<BankAccount>getBankAccountByAccountUser(@RequestBody AccountUser accountUser){
//        return bankAccountService.getBankAccountByAccountUser(accountUser);
//    }
//    @GetMapping("/bankAccountNumber")
//    public ResponseEntity<BankAccount>getBankAccountByAccountNumber(@RequestBody String accountNumber){
//        return bankAccountService.getBankAccountByAccountNumber(accountNumber);
//    }
//
//    @PostMapping("/createAccount")
//    public ResponseEntity<BankAccount>createBankAccount(@RequestBody AccountUser accountUser){
//        return bankAccountService.createBankAccount(accountUser);
//    }
//
//    @PostMapping("openingBalance")
//    public ResponseEntity<BankAccount>createBankAccount(@RequestBody AccountUser accountUser, @RequestParam double openingBal){
//        return bankAccountService.createBankAccount(accountUser, openingBal);
//    }
    @PostMapping("/deposit")
    public ResponseEntity<BankAccount>depositFund(OperationRequest request){
        return new ResponseEntity<>(operationService.depositFund(request.getAccountNumber(), request.getAmount()).getBody(), HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccount>withdrawFund(OperationRequest request){
        return new ResponseEntity<>(operationService.depositFund(request.getAccountNumber(), request.getAmount()).getBody(), HttpStatus.OK);
    }

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity<String>transferFund(OperationRequest request){
        try{
            operationService.withdrawFund(request.getAccountNumber(), request.getAmount());
            operationService.depositFund(request.getAccountNumber(), request.getAmount());
            return new ResponseEntity<>("Transaction Successful!", HttpStatus.OK);
        } catch (TransactionException transactionException){
            System.out.println(transactionException.getMessage());
        }
        return new ResponseEntity<>("Transaction Failed!", HttpStatus.NOT_ACCEPTABLE);
    }
}
