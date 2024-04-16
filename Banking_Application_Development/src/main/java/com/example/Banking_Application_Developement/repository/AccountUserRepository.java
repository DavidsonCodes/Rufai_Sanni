package com.example.Banking_Application_Developement.repository;

import com.example.Banking_Application_Developement.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Integer> {
    AccountUser findByEmail(String email);
    AccountUser findByPhoneNumber(String phoneNumber);
}
