package com.cognologix.banksystem.services;

import com.cognologix.banksystem.dto.bank.AccountDTO;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.entities.Account;

public interface AccountService {
    AccountDTO createCustomerAccount(Integer id, Account account);
    Account deposit(String accountNumber, Double depositAmount);
    Account withdraw(String accountNumber, Double withdrawAmount);
    AccountListResponse transactionBetweenCustomers(Integer customerId1, String accountNumber1, Integer customerId2, String accountNumber2, Double amount);

    AccountListResponse getAccountsByCustomerId(Integer customerId);
    AccountListResponse getAccount();
//    void depositAmount();
    String  deleteAccount(String accountNumber);

}
