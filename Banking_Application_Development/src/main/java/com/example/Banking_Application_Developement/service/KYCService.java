package com.example.Banking_Application_Developement.service;


import com.example.Banking_Application_Developement.model.KnowYourCustomer;
import com.example.Banking_Application_Developement.repository.KYCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KYCService {
    @Autowired
    private KYCRepository kycRepository;

    public ResponseEntity<List<KnowYourCustomer>>getAllKYC(){
        return new ResponseEntity<>(kycRepository.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<KnowYourCustomer>getCustomerKYCById(int id){
        return new ResponseEntity<>(kycRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<KnowYourCustomer>createCustomerKYC(KnowYourCustomer kycUser){
        return new ResponseEntity<>(kycRepository.save(kycUser), HttpStatus.CREATED);
    }
    public ResponseEntity<KnowYourCustomer>updateCustomerKYC(KnowYourCustomer kycUser, int id){
        KnowYourCustomer knowYourCustomer = kycRepository.findById(id).get();
        knowYourCustomer.setAddress(kycUser.getAddress());
        knowYourCustomer.setDateOfBirth(kycUser.getDateOfBirth());
        knowYourCustomer.setNin_number(kycUser.getNin_number());
        knowYourCustomer.setBankVerificationNumber(kycUser.getBankVerificationNumber());
        knowYourCustomer.setLocalGovtOfResidence(kycUser.getLocalGovtOfResidence());
        knowYourCustomer.setStateOfResidence(kycUser.getStateOfResidence());
        knowYourCustomer.setNextOfKin(kycUser.getNextOfKin());
        return new ResponseEntity<>(kycRepository.save(knowYourCustomer), HttpStatus.OK);
    }
}
