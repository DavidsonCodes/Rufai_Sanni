package com.example.Banking_Application_Developement.model;

import lombok.Data;

@Data
public class OperationRequest {
    private String accountNumber;
    private double amount;
}
