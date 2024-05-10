package com.example.Banking_Application_Developement.model;

import jakarta.persistence.*;

@Entity(name = "reset")
public class ResetCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;

    @OneToOne
    @JoinColumn(name = "account_user_id")
    private AccountUser accountUser;
}
