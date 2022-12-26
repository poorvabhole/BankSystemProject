package com.cognologix.banksystem.services.Implementation;

import com.cognologix.banksystem.Exception.AccountNotFoundException;
import com.cognologix.banksystem.Exception.EmptyListException;
import com.cognologix.banksystem.Exception.InsufficentBalanceException;
import com.cognologix.banksystem.dao.AccountDao;
import com.cognologix.banksystem.dao.CustomerDao;
import com.cognologix.banksystem.dao.TransactionDao;
import com.cognologix.banksystem.dto.bank.AccountDto;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.dto.bank.AccountResponse;
import com.cognologix.banksystem.dto.bank.AccountStatementResponse;
import com.cognologix.banksystem.dto.bank.DeactivateAccountResponse;
import com.cognologix.banksystem.dto.bank.TransactionDto;
import com.cognologix.banksystem.dto.bank.TransferAmountDto;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.entities.ErrorEnum;
import com.cognologix.banksystem.entities.Transaction;
import com.cognologix.banksystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private TransactionDao transactionDao;


    public static final String DEACTIVATE = "Deactivate";
    public static final String ACTIVE = "Active";

    @Override //Register/create customer
    public AccountResponse createCustomerAccount(AccountDto accountDto) {
        Account account = new Account(accountDto);
        Customer customer = customerDao.findByCustomerId(accountDto.getCustomerId());
        account.setCustomer(customer);
        accountDao.save(account);
        return new AccountResponse(account);
    }

    @Override //return list of accounts
    public AccountListResponse getAccounts() {
        List<Account> accountList = null;
        try {
            accountList = accountDao.findAll().stream()
                    .filter(account -> account.getAccountStatus().equals(ACTIVE))
                    .collect(Collectors.toList());

            if (accountList.size() == 0) {
                throw new EmptyListException(ErrorEnum.ACCOUNT_NOT_FOUND.getMessage());
            }
        } catch (final EmptyListException exception) {
            throw new EmptyListException(exception.getMessage());
        }
        return new AccountListResponse(accountList);
    }

    @Override //deposite specified amount in given account
    public TransactionDto deposit(Integer accountNumber, Double depositAmount) {
        Account currentAccount = null;
        TransactionDto transactionDto = null;
        try {
            currentAccount = accountDao.findByAccountNumber(accountNumber);
            if (currentAccount == null) {
                throw new AccountNotFoundException(ErrorEnum.ACCOUNT_NOT_FOUND.getMessage());
            }
            final Double currentBalance = currentAccount.getBalance() + depositAmount;
            currentAccount.setBalance(currentBalance);

            Transaction transaction = new Transaction(null, accountNumber, "Deposit",
                    currentBalance, null,
                    depositAmount, LocalDateTime.now());

            transactionDao.save(transaction);
            transactionDto = new TransactionDto(transaction);
            accountDao.save(currentAccount);
        } catch (final AccountNotFoundException ex) {
            throw new AccountNotFoundException(ex.getMessage());
        }
        return transactionDto;
    }

    @Override //withdraw specified amount and check sufficent balance
    public TransactionDto withdraw(Integer accountNumber, Double withdrawAmount) {
        Account currentAccount = null;
        TransactionDto transactionDto = null;
        try {
            currentAccount = accountDao.findByAccountNumber(accountNumber);
            if (currentAccount == null) {
                throw new AccountNotFoundException(ErrorEnum.ACCOUNT_NOT_FOUND.getMessage());
            }
            final Double currentBalance = currentAccount.getBalance() - withdrawAmount;
            if (currentBalance < 500) {
                throw new InsufficentBalanceException(ErrorEnum.INSUFFICENT_BALANCE.getMessage());
            }
            currentAccount.setBalance(currentBalance);

            Transaction transaction = new Transaction(accountNumber, null,
                    "withdraw", currentBalance,
                    withdrawAmount, null, LocalDateTime.now());

            transactionDao.save(transaction);
            transactionDto = new TransactionDto(transaction);
            accountDao.save(currentAccount);
        } catch (final InsufficentBalanceException exception) {
            throw new InsufficentBalanceException(exception.getMessage());
        } catch (final AccountNotFoundException ex) {
            throw new AccountNotFoundException(ex.getMessage());
        }
        return transactionDto;
    }

    @Override //transfer money from 1 customer account to second customer account
    public TransferAmountDto transactionBetweenCustomers(Integer senderAccNo, Integer receiverAccNo, Double amount) {
        TransferAmountDto transferAmountDto = null;
        try {
            Account senderAccount = accountDao.findByAccountNumber(senderAccNo);
            Account receiverAccount = accountDao.findByAccountNumber(receiverAccNo);
            if (senderAccount == null || receiverAccount == null){
                throw new AccountNotFoundException(ErrorEnum.ACCOUNT_NOT_FOUND.getMessage());
            }

            withdraw(senderAccNo, amount);
            deposit(receiverAccNo, amount);
            transferAmountDto = new TransferAmountDto(senderAccNo, receiverAccNo, amount);
        } catch (final AccountNotFoundException ex) {
            throw new AccountNotFoundException(ex.getMessage());
        }
        return transferAmountDto;
    }

    @Override
    public AccountStatementResponse getAccountStatement(Integer accountNumber) {
        try {
            List<Transaction> statement = transactionDao.findByAccountNumber(accountNumber);
            return new AccountStatementResponse(statement);
        } catch (final EmptyListException exception) {
            throw new EmptyListException(exception.getMessage());
        }
    }

    @Override //get list of accounts for specific customer
    public AccountListResponse getAccountsByCustomerId(Integer customerId) {
        List<Account> customerAccountsList = null;
        try {
            customerAccountsList = accountDao.findByCustomerId(customerId);
            if (customerAccountsList.size() == 0) {
                throw new EmptyListException(ErrorEnum.NOACCOUNT_FOR_CUSTOMER.getMessage());
            }
        } catch (EmptyListException e) {
            throw new EmptyListException(e.getMessage());
        }
        return new AccountListResponse(customerAccountsList);
    }

    @Override
    public DeactivateAccountResponse deactivateAccount(Integer accountNumber) {
        Account accountToDeactivate = null;
        try{
            accountToDeactivate = accountDao.findByAccountNumber(accountNumber);
            if (accountToDeactivate == null){
                throw new AccountNotFoundException(ErrorEnum.ACCOUNT_NOT_FOUND.getMessage());
            }
            accountToDeactivate.setAccountStatus(DEACTIVATE);
            accountDao.save(accountToDeactivate);
        }catch (final AccountNotFoundException ex) {
            throw new AccountNotFoundException(ex.getMessage());
        }
        return new DeactivateAccountResponse(true,accountNumber, accountToDeactivate.getAccountStatus());
    }
}
