package com.cognologix.banksystem.service;

import com.cognologix.banksystem.Exception.AccountNotFoundException;
import com.cognologix.banksystem.Exception.EmptyFieldException;
import com.cognologix.banksystem.Exception.EmptyListException;
import com.cognologix.banksystem.Exception.InsufficentBalanceException;
import com.cognologix.banksystem.dao.AccountDao;
import com.cognologix.banksystem.dao.CustomerDao;
import com.cognologix.banksystem.dao.TransactionDao;
import com.cognologix.banksystem.dto.bank.AccountDto;
import com.cognologix.banksystem.dto.bank.AccountResponse;
import com.cognologix.banksystem.dto.bank.AccountStatementResponse;
import com.cognologix.banksystem.dto.bank.TransactionDto;
import com.cognologix.banksystem.dto.bank.TransferAmountDto;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.entities.Transaction;
import com.cognologix.banksystem.services.Implementation.AccountServiceImpl;
import com.cognologix.banksystem.services.Implementation.CustomerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountServiceTest.class})
public class AccountServiceTest {
    @Mock
    AccountDao accountDao;
    @Mock
    TransactionDao transactionDao;
    @Mock
    CustomerDao customerDao;

    @InjectMocks
    AccountServiceImpl accountService;

    @InjectMocks
    CustomerServiceImpl customerService;

    Customer firstCustomer = new Customer(1, "Poorva Bhole", "Dhule", "12/09/1999", "ODNL66513", "214563325325");
    Customer secondCustomer = new Customer(2, "Kaustubh Bhole", "Dhule", "12/02/1995", "DBPM6876", "2145354418");
    Account firstAccount = new Account(1, "Active", "current", 1500.00, firstCustomer);
    Account secondAccount = new Account(2, "Active", "saving", 65500.00, firstCustomer);
    AccountDto accountDto = new AccountDto("current", 1500.00, 1);

    @BeforeAll
    public void setCustomer() {
        customerDao.save(firstCustomer);
        customerDao.save(secondCustomer);
    }

    @BeforeAll
    public void setAccount() {
        accountDao.save(firstAccount);
        accountDao.save(secondAccount);
    }

    @Test
    public void createAccountTest() {
        when(customerDao.findByCustomerId(accountDto.getCustomerId())).thenReturn(firstCustomer);
        when(accountDao.save(firstAccount)).thenReturn(firstAccount);
        AccountResponse actual = accountService.createCustomerAccount(accountDto);
        assertEquals(1500.00, actual.getBalance());
    }

    @Test
    public void getAccountsTest() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(firstAccount);
        accounts.add(secondAccount);
        when(accountDao.findAll()).thenReturn(accounts);
        assertEquals(2, accountService.getAccounts().getAccountsList().size());
    }

    @Test
    public void getAccount_emptyListException() {
        List<Account> accounts = new ArrayList<>();
        accountDao.saveAll(accounts);
        Exception exception = assertThrows(EmptyListException.class, () -> accountService.getAccounts());
        String expectedMessage = "Account information is empty";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void depositTest_success() {
        when(accountDao.findByAccountNumber(1)).thenReturn(firstAccount);
        Transaction transaction = new Transaction(null, 1,
                "Deposit", 2000.00, null,
                500.00, LocalDateTime.now());
        transactionDao.save(transaction);
        when(accountService.deposit(1, 500.00)).thenReturn(new TransactionDto(transaction));
        assertEquals(2000.00, transaction.getBalance());
    }

    @Test
    public void depositTest_AccountNotFoundException() {
        Exception exception = assertThrows(AccountNotFoundException.class, () -> accountService.deposit(1, 2000.00));
        String expectedMessage = "Account with given number not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void withdrawTest_success() {
        when(accountDao.findByAccountNumber(2)).thenReturn(secondAccount);
        Transaction transaction = new Transaction(2, null,
                "Withdraw", 65000.00,
                500.00, null, LocalDateTime.now());
        transactionDao.save(transaction);
        when(accountService.withdraw(2, 500.00)).thenReturn(new TransactionDto(transaction));
        assertEquals(65000.00, transaction.getBalance());
    }

    @Test
    public void withdrawTest_AccountNotFoundException() {
        Exception exception = assertThrows(AccountNotFoundException.class, () -> {
            accountService.withdraw(3, 2000.00);
        });
        String expectedMessage = "Account with given number not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void withdrawTest_InsufficentBalanceException() {
        when(accountDao.findByAccountNumber(1)).thenReturn(firstAccount);
        Exception exception = assertThrows(InsufficentBalanceException.class, () -> {
            accountService.withdraw(1, 1400.00);
        });
        String expectedMessage = "Insufficent balance, can not withdraw money";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void transactionBetweenCustomersTest_success() {
        when(accountDao.findByAccountNumber(1)).thenReturn(firstAccount);
        when(accountDao.findByAccountNumber(2)).thenReturn(secondAccount);
        TransferAmountDto transferAmountDto = new TransferAmountDto(firstAccount.getAccountNumber(),
                secondAccount.getAccountNumber(), 200.00);
        when(accountService.transactionBetweenCustomers(1, 2, 200.00)).thenReturn(transferAmountDto);
        assertEquals(200.00, transferAmountDto.getAmountTransfer());
    }

    @Test
    public void transactionBetweenCustomersTest_EmptyFieldException() {
        when(accountDao.findByAccountNumber(1)).thenReturn(firstAccount);
        when(accountDao.findByAccountNumber(2)).thenReturn(secondAccount);
        Exception exception = assertThrows(EmptyFieldException.class, () -> {
            accountService.transactionBetweenCustomers(null, 2, 50.00);
        });
        String expectedMessage = "All information is needed";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void getAccountStatementTest_success() {
        List<Transaction> statement = new ArrayList<>();
        Transaction transaction = new Transaction(null, 1,
                "Deposit", 2000.00, null,
                500.00, LocalDateTime.now());
        transactionDao.save(transaction);
        statement.add(transaction);
        when(transactionDao.findByAccountNumber(1)).thenReturn(statement);
        AccountStatementResponse response = accountService.getAccountStatement(1);
        assertEquals(1, response.getStatement().size());
        assertEquals(2000.00, response.getStatement().get(0).getBalance());
    }
}
