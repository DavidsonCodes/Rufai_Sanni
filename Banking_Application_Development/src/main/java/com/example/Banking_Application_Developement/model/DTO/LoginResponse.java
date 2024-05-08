package com.example.Banking_Application_Developement.model.DTO;

import com.example.Banking_Application_Developement.model.AccountUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private AccountUser accountUser;
    private String token;
}
