package com.example.Banking_Application_Developement.controller;


import com.example.Banking_Application_Developement.model.KnowYourCustomer;
import com.example.Banking_Application_Developement.service.KYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class KYCController {
    @Autowired
    private KYCService kycService;

    @GetMapping("/kyc")
    public ResponseEntity<List<KnowYourCustomer>> getAllKYC(){
        return kycService.getAllKYC();
    }
    @GetMapping("/kyc/{id}")
    public ResponseEntity<KnowYourCustomer>getCustomerKYCById(@PathVariable int id){
        return kycService.getCustomerKYCById(id);
    }

    @PostMapping("/kyc")
    public ResponseEntity<KnowYourCustomer>createCustomerKYC(@RequestBody KnowYourCustomer customer){
        return kycService.createCustomerKYC(customer);
    }
    @PutMapping("/kyc/{id}")
    public ResponseEntity<KnowYourCustomer>updateCustomerKYC(@RequestBody KnowYourCustomer customer, @PathVariable int id){
        return kycService.updateCustomerKYC(customer, id);
    }
}
