package com.example.Banking_Application_Developement.repository;

import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.model.KnowYourCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCRepository extends JpaRepository<KnowYourCustomer, Integer> {
    KnowYourCustomer getByAccountUser(AccountUser accountUser);
}
