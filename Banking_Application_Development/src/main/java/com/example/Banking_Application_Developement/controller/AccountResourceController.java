package com.example.Banking_Application_Developement.controller;

import com.example.Banking_Application_Developement.model.AccountResource;
import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.service.AccountUserService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.HtmlEscapeTag;


@RestController
@RequestMapping("/resources")
public class AccountResourceController {
    private AccountUserService accountUserService;

    public AccountResourceController(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<AccountResource> getAccountResource(@PathVariable int id){
        AccountUser accountUserToSend = accountUserService.getAccountUserById(id).getBody();
        AccountResource accountResource = new AccountResource();
        accountResource.setAccountUser(accountUserToSend);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAccountUserById(id)).withSelfRel();
        Link delete = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).deleteAccountUser(id)).withRel("delete");
        Link update = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).updateAccountUser(id, accountUserToSend)).withRel("update");
        Link allAccounts = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAllAccountUsers()).withRel("allAccounts");
        accountResource.add(selfLink, delete, update, allAccounts);
        return  new ResponseEntity<>(accountResource, HttpStatus.OK);
    }
}
