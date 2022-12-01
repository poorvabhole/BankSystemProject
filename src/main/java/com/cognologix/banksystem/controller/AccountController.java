package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.Exception.CustomerNotFoundException;
import com.cognologix.banksystem.dto.bank.AccountDTO;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.services.Implementation.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/banksystem/account")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    /**
     * Gets all accounts
     *
     * @return account list response
     */
    @GetMapping("/getaccount")
    public ResponseEntity<AccountListResponse> getAccount() {
        AccountListResponse response = accountService.getAccount();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Gets accounts according to customer id
     *
     * @param customerId
     * @return account list response
     */
    @GetMapping("/customeraccounts")
    public ResponseEntity<AccountListResponse> accountsByCustomerId(@PathVariable Integer customerId) {
        AccountListResponse response = accountService.getAccountsByCustomerId(customerId);
        return new ResponseEntity<>(accountService.getAccountsByCustomerId(customerId), HttpStatus.OK);
    }

    /**
     * creates bank account for given cusromerId
     *
     * @param id      customerId
     * @param account
     * @return response entity
     */
    @PostMapping("/createcustomeraccount/{id}")
    public ResponseEntity<AccountDTO> createAccount(@PathVariable Integer id, @Valid @RequestBody Account account) {
        AccountDTO accountDTO = accountService.createCustomerAccount(id, account);
        accountDTO.setMessage("Account created successfully");
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    /**
     * Deposit amount
     *
     * @param accountNumber
     * @param amount
     * @return reponse entity
     */
    @PutMapping("/depositAmount/{accountNumber}/{amount}")
    public ResponseEntity<Account> depositAmount(@PathVariable String accountNumber, @PathVariable Double amount) {
        return new ResponseEntity<>(accountService.deposit(accountNumber, amount), HttpStatus.OK);
    }

    /**
     * withdraw amount
     *
     * @param accountNumber
     * @param amount
     * @return reaponse entity
     */
    @PutMapping("/withdrawAmount/{accountNumber}/{amount}")
    public ResponseEntity<Account> withdrawAmount(@PathVariable String accountNumber, @PathVariable Double amount) {
        return new ResponseEntity<>(accountService.withdraw(accountNumber, amount), HttpStatus.OK);
    }

    /**
     * transfer money from one customer to another
     *
     * @param customerId1
     * @param customerId2
     * @param senderAccountNo
     * @param receiverAccountNo
     * @param amount
     * @return response entity
     */
    @PutMapping("/transfermoney")
    public ResponseEntity<AccountListResponse> transactionBetweenCustomers(@PathParam("customerId1") Integer customerId1,
                                                                           @PathParam("senderAccountNo") String senderAccountNo,
                                                                           @PathParam("customerId2") Integer customerId2,
                                                                           @PathParam("receiverAccountNo") String receiverAccountNo,
                                                                           @PathParam(value = "amount") Double amount) {
        return new ResponseEntity<>(accountService.transactionBetweenCustomers(customerId1, senderAccountNo, customerId2, receiverAccountNo, amount), HttpStatus.OK);
    }

    /**
     * Deletes account
     *
     * @param accountNumber
     * @return reponse entity
     */
    @DeleteMapping("/deleteaccount/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber) {
        return new ResponseEntity<>(accountService.deleteAccount(accountNumber), HttpStatus.OK);
    }

}
