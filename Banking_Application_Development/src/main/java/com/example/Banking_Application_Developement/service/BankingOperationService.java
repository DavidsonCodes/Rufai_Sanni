package com.example.Banking_Application_Developement.service;

import com.example.Banking_Application_Developement.exception.TransactionException;
import com.example.Banking_Application_Developement.model.BankAccount;
import com.example.Banking_Application_Developement.model.TransactionType;
import com.example.Banking_Application_Developement.model.Transactions;
import com.example.Banking_Application_Developement.service.BankAccountService;
import com.example.Banking_Application_Developement.service.TransactionsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
public class BankingOperationService {
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private TransactionsService transactionsService;

    // Deposit Funds
    public ResponseEntity<BankAccount> depositFund(String accountNumber, double amount){
        if(amount < 1){
            throw new TransactionException("You cannot deposit negative amount");
        }
        BankAccount account = bankAccountService.getBankAccountByAccountNumber(accountNumber).getBody();
        assert account != null;

        account.setAccountBalance(amount + account.getAccountBalance());

        Transactions transaction = new Transactions();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transactionsService.postTransactions(transaction);

        return new ResponseEntity<>(bankAccountService.updateBankAccount(account).getBody(), HttpStatus.OK);
    }

    // Withdrawing Funds
    public ResponseEntity<BankAccount> withdrawFund(String accountNumber, double amount){
        if(amount < 1){
            throw new TransactionException("You cannot deposit negative amount");
        }
        BankAccount account = bankAccountService.getBankAccountByAccountNumber(accountNumber).getBody();
        assert account != null;

        account.setAccountBalance(account.getAccountBalance() - amount);

        Transactions transaction = new Transactions();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transactionsService.postTransactions(transaction);

        return new ResponseEntity<>(bankAccountService.updateBankAccount(account).getBody(), HttpStatus.OK);
    }

    // Doing transfer from ne account to another
    @Transactional
    public ResponseEntity<String> transferFund(String accountFrom, String accountTo, double amount){
        try{
            withdrawFund(accountFrom, amount);
            depositFund(accountTo, amount);
            return new ResponseEntity<>("Transaction Successful!", HttpStatus.OK);
        } catch (TransactionException transactionException){
            System.out.println(transactionException.getMessage());
        }
        return new ResponseEntity<>("Transaction Failed!", HttpStatus.NOT_ACCEPTABLE);
    }
}
