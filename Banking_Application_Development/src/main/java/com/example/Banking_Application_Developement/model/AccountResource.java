package com.example.Banking_Application_Developement.model;

import org.springframework.hateoas.RepresentationModel;

public class AccountResource extends RepresentationModel<AccountUser> {
    private AccountUser accountUser;

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }
}
