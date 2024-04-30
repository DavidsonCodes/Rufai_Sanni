package com.example.Banking_Application_Developement.model;


import jakarta.persistence.*;
import lombok.Data;

//@Data
@Entity
@Table(name="kyc")
public class KnowYourCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  String address;
    private String bankVerificationNumber;
    private String nin_number;
    private String localGovtOfResidence;
    private String stateOfResidence;
    private String dateOfBirth;
    private String nextOfKin;

    @OneToOne
    @JoinColumn(name = "account_user_id")
    private  AccountUser accountUser;

    public KnowYourCustomer() {
    }

    public KnowYourCustomer(String address, String bankVerificationNumber, String nin_number, String localGovtOfResidence, String stateOfResidence, String dateOfBirth, String nextOfKin, AccountUser accountUser) {
        this.address = address;
        this.bankVerificationNumber = bankVerificationNumber;
        this.nin_number = nin_number;
        this.localGovtOfResidence = localGovtOfResidence;
        this.stateOfResidence = stateOfResidence;
        this.dateOfBirth = dateOfBirth;
        this.nextOfKin = nextOfKin;
        this.accountUser = accountUser;
    }

    public Integer getId(int id) {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankVerificationNumber() {
        return bankVerificationNumber;
    }

    public void setBankVerificationNumber(String bankVerificationNumber) {
        this.bankVerificationNumber = bankVerificationNumber;
    }

    public String getNin_number() {
        return nin_number;
    }

    public void setNin_number(String nin_number) {
        this.nin_number = nin_number;
    }

    public String getLocalGovtOfResidence() {
        return localGovtOfResidence;
    }

    public void setLocalGovtOfResidence(String localGovtOfResidence) {
        this.localGovtOfResidence = localGovtOfResidence;
    }

    public String getStateOfResidence() {
        return stateOfResidence;
    }

    public void setStateOfResidence(String stateOfResidence) {
        this.stateOfResidence = stateOfResidence;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }
}
