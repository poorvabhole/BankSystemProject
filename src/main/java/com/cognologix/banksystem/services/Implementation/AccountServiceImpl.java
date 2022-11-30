package com.cognologix.banksystem.services.Implementation;

import com.cognologix.banksystem.Exception.EmptyFieldException;
import com.cognologix.banksystem.Exception.EmptyListException;
import com.cognologix.banksystem.Exception.InsufficentBalanceException;
import com.cognologix.banksystem.dao.AccountDao;
import com.cognologix.banksystem.dao.CustomerDao;
import com.cognologix.banksystem.dao.TransactionDao;
import com.cognologix.banksystem.dto.bank.AccountDto;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.dto.bank.AccountResponse;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.entities.Transaction;
import com.cognologix.banksystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private TransactionDao transactionDao;

    @Override //Register/create customer
    public AccountResponse createCustomerAccount(AccountDto accountDto) {
        Account account = new Account();
        Integer id = accountDto.getCustomerId();
        Customer customer = customerDao.findById(id).get();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBalance(accountDto.getBalance());
        account.setCustomer(customer);
        accountDao.save(account);
        return new AccountResponse(account);
    }

    @Override //return list of accounts
    public AccountListResponse getAccount() {
        List<Account> accountList = null;
        try {
            accountList = accountDao.findAll();
            if (accountList.size() == 0) {
                throw new EmptyListException("Account information is empty");
            }
        } catch (final EmptyListException exception) {
            System.out.println("Error message ==> " + exception.getMessage());
        }
        return new AccountListResponse(accountList);
    }

    @Override //deposite specified amount in given account
    public Account deposit(Long accountNumber, Double depositAmount) {
        Account currentAccount = null;
        try {
            currentAccount = accountDao.findByAccountNumberEquals(accountNumber);
            final Double currentBalance = currentAccount.getBalance() + depositAmount;
            currentAccount.setBalance(currentBalance);
            Transaction transaction = new Transaction();
            transaction.setSenderAccountNumber(null);
            transaction.setReceiverAccountNumber(accountNumber);
            transaction.setBalance(currentBalance);
            transaction.setType("Deposit");
            transactionDao.save(transaction);
            accountDao.save(currentAccount);
        } catch (final Exception ex) {
            System.out.println("Error message ==> " + ex.getMessage());
        }
        return currentAccount;
    }

    @Override //withdraw specified amount and check sufficent balance
    public Account withdraw(Long accountNumber, Double withdrawAmount) {
        Account currentAccount = null;
        try {
            currentAccount = accountDao.findByAccountNumberEquals(accountNumber);
            final Double currentBalance = currentAccount.getBalance() - withdrawAmount;
            if (currentBalance < 500) {
                throw new InsufficentBalanceException("Insufficent balance, can not withdraw money");
            }
            currentAccount.setBalance(currentBalance);
            Transaction transaction = new Transaction();
            transaction.setSenderAccountNumber(accountNumber);
            transaction.setReceiverAccountNumber(null);
            transaction.setBalance(currentBalance);
            transaction.setType("withdraw");
            transaction.setDate(LocalDateTime.now());
            transactionDao.save(transaction);
            accountDao.save(currentAccount);
            return currentAccount;

        } catch (final InsufficentBalanceException exception) {
//            System.out.println("Error message ==> " + exception.getMessage());
//            exception.printStackTrace();
            throw new InsufficentBalanceException(exception.getMessage());
        }
    }

    @Override //transfer money from 1 customer account to second customer account
    public String transactionBetweenCustomers(Long senderAccNo, Long receiverAccNo, Double amount) {
        List<Account> updatedAccountsList = null;
        try {
            withdraw(senderAccNo, amount);
            deposit(receiverAccNo, amount);
        } catch (final Exception ex) {
            System.out.println("Error message ==> " + ex.getMessage());
        }
        return "Transaction Done";
    }

    @Override
    public List<Transaction> getAccountStatement(Long accountNumber) {
//         statement = null;
        try {
            List<Transaction> statement = transactionDao.findBySenderAccountNumber(accountNumber);
            System.out.println(statement);
            return statement;
        } catch (final EmptyListException exception) {
            System.out.println("Error message ==> " + exception.getMessage());
            throw new EmptyFieldException(exception.getMessage());
        }

    }
//
//    @Override //get list of accounts for specific customer
//    public AccountListResponse getAccountsByCustomerId(Integer customerId) {
//        List<Account> customerAccountsList = null;
//        try {
//            customerAccountsList = accountDao.findByCustomerId(customerId);
//            if (customerAccountsList.size() == 0){
//                throw new EmptyListException("Account for given customer Id is not present");
//            }
//        }catch (EmptyListException e){
//            e.getMessage();
//        }
//        return new AccountListResponse(customerAccountsList);
//    }
}
