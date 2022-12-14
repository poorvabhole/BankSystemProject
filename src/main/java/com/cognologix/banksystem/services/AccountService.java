package com.cognologix.banksystem.services;

import com.cognologix.banksystem.dto.bank.AccountDto;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.dto.bank.AccountResponse;
import com.cognologix.banksystem.dto.bank.AccountStatementResponse;
import com.cognologix.banksystem.dto.bank.TransactionDto;
import com.cognologix.banksystem.dto.bank.TransferAmountDto;

public interface AccountService {
    AccountResponse createCustomerAccount(AccountDto account);

    TransactionDto deposit(Integer accountNumber, Double depositAmount);

    TransactionDto withdraw(Integer accountNumber, Double withdrawAmount);

    TransferAmountDto transactionBetweenCustomers(Integer senderAccNo, Integer receiverAccNo, Double amount);

    AccountStatementResponse getAccountStatement(Integer accountNumber);

    AccountListResponse getAccountsByCustomerId(Integer customerId);

    AccountListResponse getAccount();
    String deactivateAccount(Integer accountNumber);

}
