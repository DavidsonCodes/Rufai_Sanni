package com.example.Banking_Application_Developement.service;


import com.example.Banking_Application_Developement.model.Transactions;
import com.example.Banking_Application_Developement.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

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
    public ResponseEntity<Transactions>postTransactions(Transactions transactions){
        StringBuilder transId = new StringBuilder();
        transId.append("TXN");
        for(int i=0; i <10; i++){
            int random = new Random().nextInt(10);
            transId.append(random);
        }
        transactions.setTransactionsId(transId.toString());
        transactions.setTransactionDate(new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(transactionRepository.save(transactions), HttpStatus.CREATED);
    }
}
