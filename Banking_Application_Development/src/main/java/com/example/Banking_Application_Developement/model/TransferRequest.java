package com.example.Banking_Application_Developement.model;

import lombok.Data;

@Data
public class TransferRequest {
    private String accountFrom;
    private String accountTo;
    private Double amount;
}
