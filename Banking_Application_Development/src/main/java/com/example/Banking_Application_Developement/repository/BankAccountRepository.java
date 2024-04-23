package com.example.Banking_Application_Developement.repository;

import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount getByAccountUser(AccountUser accountUser);
}
