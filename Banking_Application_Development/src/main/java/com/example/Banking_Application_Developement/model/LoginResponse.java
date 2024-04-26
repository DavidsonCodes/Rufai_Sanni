package com.example.Banking_Application_Developement.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private AccountUser accountUser;
    private String token;
}
