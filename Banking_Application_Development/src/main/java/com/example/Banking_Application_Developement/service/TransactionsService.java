package com.example.Banking_Application_Developement.service;


import com.example.Banking_Application_Developement.model.Transactions;
import com.example.Banking_Application_Developement.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {
    @Autowired
    private TransactionsRepository transactionRepository;
    public ResponseEntity<List<Transactions>>getAllTransactions(){
        return new ResponseEntity<>(transactionRepository.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<Transactions>getById(long id){
        return new ResponseEntity<>(transactionRepository.findById(id), HttpStatus.OK);
    }
    public ResponseEntity<Transactions>getByTransactionsId(String transactionId){
        return new ResponseEntity<>(transactionRepository.findByTransactionsId(transactionId), HttpStatus.OK);
    }
    public ResponseEntity<Transactions>posTransactions(Transactions transactions){
        return new ResponseEntity<>(transactionRepository.save(transactions), HttpStatus.CREATED);
    }
}
