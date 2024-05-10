package com.example.Banking_Application_Developement.controller;


import com.example.Banking_Application_Developement.model.Transactions;
import com.example.Banking_Application_Developement.repository.TransactionsRepository;
import com.example.Banking_Application_Developement.service.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    // Create the mapping endpoints
    @GetMapping("/allTransactions")
    public ResponseEntity<List<Transactions>> getAllTransactions(){
        return transactionsService.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transactions> getById(@PathVariable long id){
        return transactionsService.getById(id);
    }

    @GetMapping("/transactions/{transaction_id}")
    public ResponseEntity<Transactions> getById(@PathVariable String transaction_id){
        return transactionsService.getByTransactionsId(transaction_id);
    }
    @PostMapping("/transactions")
    public ResponseEntity<Transactions> createTransaction(@RequestBody @Valid Transactions transactions){
        return transactionsService.postTransactions(transactions);
    }

}
