package com.cognologix.banksystem.services.Implementation;

import com.cognologix.banksystem.Exception.AccountNotFoundException;
import com.cognologix.banksystem.Exception.EmptyListException;
import com.cognologix.banksystem.Exception.InsufficentBalanceException;
import com.cognologix.banksystem.dao.AccountDao;
import com.cognologix.banksystem.dto.bank.AccountDTO;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override //Register/create customer
    public AccountDTO createCustomerAccount(Integer id, Account account) {
        Account newAccount = null;
        try {
            newAccount = accountDao.createAccount(id, account);
        }catch (final Exception exception){
            exception.getMessage();
        }
        return new AccountDTO(newAccount);
    }

    @Override //return list of accounts
    public AccountListResponse getAccount() {
        List<Account> accountList = null;
        try {
            accountList = accountDao.findAll();
            if (accountList.size() == 0){
                throw  new EmptyListException("Account information is empty");
            }
        }catch (final EmptyListException exception){
            exception.getMessage();
        }
        return new AccountListResponse(accountList);
    }

    @Override // delete account with specified account number
    public String deleteAccount(String accountNumber) {
        String message = null;
        try {
            if (accountDao.delete(accountNumber)){
                message = "Account deleted";
            }else {
                throw new AccountNotFoundException("Account not found");
            }
        }catch (final AccountNotFoundException ex){
            ex.getMessage();
        }
        return message;
    }

    @Override //deposite specified amount in given account
    public Account deposit(String accountNumber, Double depositAmount) {
        Account currentAccountUpdate = null;
        try {
            final List<Account> currentAccount = accountDao.findById(accountNumber);
            final Double currentBalance = currentAccount.get(0).getBalance() + depositAmount;
            currentAccountUpdate = currentAccount.get(0);
            currentAccountUpdate.setBalance(currentBalance);
        }catch (final Exception ex){
            ex.getMessage();
        }
        return currentAccountUpdate;
    }


    @Override //withdraw specified amount and check sufficent balance
    public Account withdraw(String accountNumber, Double withdrawAmount) {
        Account currentAccountUpdate = null;
        try {
            List<Account> currentAccount = accountDao.findById(accountNumber);
            final Double currentBalance = currentAccount.get(0).getBalance() - withdrawAmount;
            if (currentBalance < 500){
                throw new InsufficentBalanceException("Insufficent balance, can not withdraw money");
            }
            currentAccountUpdate = currentAccount.get(0);
            currentAccountUpdate.setBalance(currentBalance);
        }catch (final InsufficentBalanceException exception){
            exception.getMessage();
        }
        return currentAccountUpdate;
    }

    @Override //transfer money from 1 customer account to second customer account
    public AccountListResponse transactionBetweenCustomers(Integer customerId1, String accountNumber1, Integer customerId2, String accountNumber2, Double amount) {
        List<Account> updatedAccountsList = null;
        try {
            Account accountCustomer1 = withdraw(accountNumber1, amount);
            Account accountCustomer2 = deposit(accountNumber2, amount);
            updatedAccountsList = new ArrayList<>();
            updatedAccountsList.add(accountCustomer1);
            updatedAccountsList.add(accountCustomer2);
        }catch (final Exception ex){
            ex.getMessage();
        }
        return new AccountListResponse(updatedAccountsList);
    }

    @Override //get list of accounts for specific customer
    public AccountListResponse getAccountsByCustomerId(Integer customerId) {
        List<Account> customerAccountsList = null;
        try {
            customerAccountsList = accountDao.findByCustomerId(customerId);
            if (customerAccountsList.size() == 0){
                throw new EmptyListException("Account for given customer Id is not present");
            }
        }catch (EmptyListException e){
            e.getMessage();
        }
        return new AccountListResponse(customerAccountsList);
    }
}
