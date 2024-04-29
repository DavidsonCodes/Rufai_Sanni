package com.example.Banking_Application_Developement.repository;

import com.example.Banking_Application_Developement.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    Transactions findById(long id);
    Transactions findByTransactionsId(String transaction_id);
}
