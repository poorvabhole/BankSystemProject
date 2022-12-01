package com.cognologix.banksystem.dao;

import com.cognologix.banksystem.Exception.EmptyListException;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class AccountDao {
    @Autowired
    private CustomerDao customerDao;
    List<Account> accountList = new ArrayList<>();

    public Account createAccount(Integer id, Account newAccount) {
        List<Customer> customerList = customerDao.findAll();
        List<Customer> customer1 = customerList.stream().filter(customer -> customer.getCustomerId() == id).collect(Collectors.toList());

        newAccount.setCustomerId(customer1.get(0).getCustomerId());
        accountList.add(newAccount);
        return newAccount;
    }

    public List<Account> findAll() {
        return accountList;
    }

    //method to find account by account number
    public List<Account> findById(String id) {
        List<Account> account = accountList.stream().filter(accountlist -> accountlist.getAccountId().equals(id)).collect(Collectors.toList());
        return account;
    }

    //method to find account by given customer id
    public List<Account> findByCustomerId(Integer customerId) {
        List<Account> accountLists = accountList.stream().filter(accountlist -> accountlist.getCustomerId().equals(customerId)).collect(Collectors.toList());
        return accountLists;
    }

    //method deletes account having specified account number
    public boolean delete(String accountNumber) {
        List<Account> list = accountList.stream().filter(accountlist -> accountlist.getAccountId().equals(accountNumber)).collect(Collectors.toList());
        return accountList.remove(list.get(0));
    }

    public boolean deleleByCustomerId(Integer customerId) {
        List<Account> accountLists = accountList.stream().filter(accountlist -> accountlist.getCustomerId().equals(customerId)).collect(Collectors.toList());
        return accountList.removeAll(accountLists);
    }

}
