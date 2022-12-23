package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.Exception.AccountNotFoundException;
import com.cognologix.banksystem.Exception.InsufficentBalanceException;
import com.cognologix.banksystem.dto.bank.AccountDto;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.dto.bank.AccountResponse;
import com.cognologix.banksystem.dto.bank.AccountStatementResponse;
import com.cognologix.banksystem.dto.bank.DeactivateAccountResponse;
import com.cognologix.banksystem.dto.bank.TransactionDto;
import com.cognologix.banksystem.dto.bank.TransferAmountDto;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.entities.Transaction;
import com.cognologix.banksystem.services.AccountService;
import com.cognologix.banksystem.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AccountService accountService;

    @MockBean
    private CustomerService customerService;


    @Test
    void getStatement() throws Exception {
        List<Account> accountList = new ArrayList<>();
        Integer customerId = 1;
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");

        Account account = new Account();
        account.setAccountNumber(1);
        account.setCustomer(customer);
        accountList.add(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setBalance(1500.00);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        AccountStatementResponse response = new AccountStatementResponse(transactions);

        when(accountService.getAccountStatement(1)).thenReturn(response);

        mockMvc.perform(get("/banksystem/account/getstatement?accountNumber=1"))
                .andExpect(status().isOk());

    }

    @Test
    void accountsByCustomerId() throws Exception {
        List<Account> accountList = new ArrayList<>();
        Integer customerId = 1;
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");

        Account account = new Account();
        account.setAccountNumber(1);
        account.setCustomer(customer);
        accountList.add(account);


        AccountListResponse listResponse = new AccountListResponse(accountList);

        when(accountService.getAccountsByCustomerId(1)).thenReturn(listResponse);

        mockMvc.perform(get("/banksystem/account/customeraccounts/{customerId}", customerId))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.accountsList.size()", is(listResponse.getAccountsList().size())));
    }

    @Test
    public void accountsByCustomerId_CustomerNotFoundException() throws Exception {
        List<Account> accountList = new ArrayList<>();
        Integer customerId = 2;

        AccountListResponse listResponse = new AccountListResponse(accountList);
        when(accountService.getAccountsByCustomerId(2)).thenReturn(listResponse);

        mockMvc.perform(get("/banksystem/account/customeraccounts/{customerId}", customerId))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccount() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");
        Account account = new Account();
        account.setAccountNumber(1);
        account.setCustomer(customer);

        AccountResponse accountResponse = new AccountResponse(account);
        AccountDto accountDto = new AccountDto("saving", 1200.00, 1);

        when(accountService.createCustomerAccount(accountDto)).thenReturn(accountResponse);

        mockMvc.perform(post("/banksystem/account/createcustomeraccount")
                        .content(objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void depositAmount() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");
        Account account = new Account();
        account.setAccountNumber(1);
        account.setCustomer(customer);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setDepositAmount(100.00);
        transaction.setSenderAccountNumber(1);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(1);
        transactionDto.setDepositAmount(100.00);

        when(accountService.deposit(1, 100.00)).thenReturn(transactionDto);

        mockMvc.perform(put("/banksystem/account/depositAmount/{accountNumber}/{amount}", 1, 100.00)
                        .content(objectMapper.writeValueAsString(transactionDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void depositAmount_negative() throws Exception {
        TransactionDto transactionDto = new TransactionDto();

        when(accountService.deposit(2, 100.00)).thenReturn(transactionDto);

        mockMvc.perform(put("/banksystem/account/depositAmount/{accountNumber}/{amount}", 2, 100.00)
                        .content(objectMapper.writeValueAsString(transactionDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void withdrawAmount() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");
        Account account = new Account();
        account.setAccountNumber(1);
        account.setCustomer(customer);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setWithdrawAmount(566.00);
        transaction.setReceiverAccountNumber(1);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(1);
        transactionDto.setWithdrawAmount(566.00);

        when(accountService.withdraw(1, 566.00)).thenReturn(transactionDto);

        mockMvc.perform(put("/banksystem/account/withdrawAmount/{accountNumber}/{amount}", 1, 566.00)
                        .content(objectMapper.writeValueAsString(transactionDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void withdrawAmount_AccountNotFoundException() throws Exception {
        TransactionDto transactionDto = new TransactionDto();

        when(accountService.withdraw(2, 100.00)).thenReturn(transactionDto);

        mockMvc.perform(put("/banksystem/account/withdrawAmount/{accountNumber}/{amount}", 2, 100.00)
                        .content(objectMapper.writeValueAsString(transactionDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void withdrawAmount_InsufficentBalanceException() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");
        Account account = new Account();
        account.setAccountNumber(1);
        account.setBalance(1500.00);
        account.setCustomer(customer);

        TransactionDto transactionDto = new TransactionDto();
        String message = "Error : Insufficent balance, can not withdraw money";

        when(accountService.withdraw(1, 1500.00)).thenThrow(
                new InsufficentBalanceException("Insufficent balance, can not withdraw money"));

        mockMvc.perform(put("/banksystem/account/withdrawAmount/{accountNumber}/{amount}", 1, 1500.00)
                        .content(objectMapper.writeValueAsString(transactionDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", is(message)));
    }

    @Test
    void transactionBetweenCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");

        Account account = new Account();
        account.setAccountNumber(1);
        account.setCustomer(customer);

        Customer secondCustomer = new Customer();
        secondCustomer.setCustomerId(2);
        secondCustomer.setFullName("Poorva");

        Account secondAccount = new Account();
        secondAccount.setAccountNumber(2);
        secondAccount.setCustomer(secondCustomer);

        TransferAmountDto transferAmountDto = new TransferAmountDto();
        transferAmountDto.setAmountTransfer(120.00);
        transferAmountDto.setReceiverAccountNo(2);
        transferAmountDto.setSenderAccountNo(1);

        when(accountService.transactionBetweenCustomers(1, 2, 120.00)).thenReturn(transferAmountDto);

        mockMvc.perform(put("/banksystem/account/transfermoney?senderAccNo=1&receiverAccNo=2&amount=120.00")
                        .content(objectMapper.writeValueAsString(transferAmountDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void transactionBetweenCustomers_AccountNotFondException() throws Exception {
        TransferAmountDto transferAmountDto = new TransferAmountDto();

        when(accountService.transactionBetweenCustomers(1, 2, 120.00)).
                thenThrow(new AccountNotFoundException("Account with given number not found"));

        mockMvc.perform(put("/banksystem/account/transfermoney?senderAccNo=1&receiverAccNo=2&amount=120.00")
                        .content(objectMapper.writeValueAsString(transferAmountDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deactivateAccount() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");
        Account account = new Account();
        account.setAccountNumber(1);
        account.setCustomer(customer);

        DeactivateAccountResponse response = new DeactivateAccountResponse();
        response.setAccountNumber(1);
        response.setAccountStatus("Deactivate");

        when(accountService.deactivateAccount(1)).thenReturn(response);

        mockMvc.perform(delete("/banksystem/account/deactivateaccount?accountNumber=1")
                        .content(objectMapper.writeValueAsString(response))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber", is(account.getAccountNumber())));
    }

    @Test
    void deactivateAccount_AccountNotFoundException() throws Exception {
        DeactivateAccountResponse response = new DeactivateAccountResponse();

        when(accountService.deactivateAccount(1)).
                thenThrow(new AccountNotFoundException("Account with given number not found"));

        mockMvc.perform(delete("/banksystem/account/deactivateaccount?accountNumber=1")
                        .content(objectMapper.writeValueAsString(response))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}