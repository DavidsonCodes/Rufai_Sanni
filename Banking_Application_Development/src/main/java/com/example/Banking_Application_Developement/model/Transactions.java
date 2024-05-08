package com.example.Banking_Application_Developement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Entity(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String transactionsId;

    @NotNull
    @Length(min = 10)
    private String accountNumber;

//    @NotNull
//    @Length(min = 10)
//    private String accountTo;

    private Date transactionDate;

    private double amount;

    @Enumerated(EnumType.STRING)
    private  TransactionType transactionType;
}
