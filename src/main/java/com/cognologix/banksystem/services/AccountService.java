package com.cognologix.banksystem.services;

import com.cognologix.banksystem.dto.bank.AccountDto;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.dto.bank.AccountResponse;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.entities.Transaction;

import java.util.List;

public interface AccountService {
    AccountResponse createCustomerAccount(AccountDto account);

    Account deposit(Long accountNumber, Double depositAmount);

    Account withdraw(Long accountNumber, Double withdrawAmount);

    String transactionBetweenCustomers(Long senderAccNo, Long receiverAccNo, Double amount);

    List<Transaction> getAccountStatement(Long accountNumber);

    //    AccountListResponse getAccountsByCustomerId(Integer customerId);
    AccountListResponse getAccount();

    //withdraw specified amount and check sufficent balance
//    void depositAmount();
//    String  deleteAccount(Integer accountNumber);

}
