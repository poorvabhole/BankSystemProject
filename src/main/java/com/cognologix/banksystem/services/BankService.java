package com.cognologix.banksystem.services;

import com.cognologix.banksystem.dto.bank.BankAccountDTO;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.entities.BankAccount;
import com.cognologix.banksystem.entities.Customer;

import java.util.List;

public interface BankService {
    //    AccountResponse createAccount(BankAccountDTO account);



    BankAccountDTO createAccount(BankAccount account);
//    AccountResponse getBalance(Long accountNumber);
////    Customer getCustomer(String name);
//    BaseResponse deposite(Double amount);
//    BaseResponse withdrawl(Double amount);
}
