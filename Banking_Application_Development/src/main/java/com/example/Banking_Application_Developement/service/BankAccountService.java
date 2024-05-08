package com.example.Banking_Application_Developement.service;


import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.model.BankAccount;
import com.example.Banking_Application_Developement.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public ResponseEntity<List<BankAccount>>getAllBankAccounts(){
        return new ResponseEntity<>(bankAccountRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount>getBankAccountById(int id){
        return new ResponseEntity<>(bankAccountRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount>getBankAccountByAccountUser(AccountUser accountUser){
        return new ResponseEntity<>(bankAccountRepository.getByAccountUser(accountUser), HttpStatus.OK);
    }
    public ResponseEntity<BankAccount>getBankAccountByAccountNumber(String accountNumber){
        return new ResponseEntity<>(bankAccountRepository.getBankAccountByAccountNumber(accountNumber), HttpStatus.OK);
    }
    public ResponseEntity<BankAccount>updateBankAccount(BankAccount bankAccount){
        return new ResponseEntity<>(bankAccountRepository.save(bankAccount), HttpStatus.OK);
    }

    public ResponseEntity<BankAccount>createBankAccount(AccountUser accountUser, double openingBalance){

        long randomNumbers = (long)(Math.random() * 10000000000L);
        String accountNumber = String.valueOf(randomNumbers);

        /*
        * StringBuilder accountNumber = new StringBuilder();
        * int count = 0;
        * while(count < accountNumber.length){
        *   int randomInt = new Random().nextInt(10);
        *   accountNumber.append(randomInt);
        * }
        * */

        BankAccount bankAccount = new BankAccount(accountUser, openingBalance, accountNumber);
        return new ResponseEntity<>(bankAccountRepository.save(bankAccount), HttpStatus.CREATED);
    }
    public ResponseEntity<BankAccount>createBankAccount(AccountUser accountUser){

        long randomNumbers = (long)(Math.random() * 10000000000L);
        String accountNumber = String.valueOf(randomNumbers);

        /*
         * StringBuilder accountNumber = new StringBuilder();
         * int count = 0;
         * while(count < accountNumber.length){
         *   int randomInt = new Random().nextInt(10);
         *   accountNumber.append(randomInt);
         * }
         * */

        BankAccount bankAccount = new BankAccount(accountUser, 0.00, accountNumber);
        return new ResponseEntity<>(bankAccountRepository.save(bankAccount), HttpStatus.CREATED);
    }
}
