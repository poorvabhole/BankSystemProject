package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.dto.bank.AccountDto;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.dto.bank.AccountResponse;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.entities.Transaction;
import com.cognologix.banksystem.services.Implementation.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.List;

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
        response.setMessage("Account list");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getstatement")
    public ResponseEntity<List<Transaction>> getStatement(@PathParam("accountNumber") Long accountNumber){
        return new ResponseEntity<>(accountService.getAccountStatement(accountNumber),HttpStatus.OK);
    }

    /**
     * Gets accounts according to customer id
     *
     * @param customerId
     * @return account list response
     * */
//    @GetMapping("/customeraccounts")
//    public ResponseEntity<AccountListResponse> accountsByCustomerId(@PathVariable Integer customerId) {
//        AccountListResponse response =accountService.getAccountsByCustomerId(customerId);
//        return new ResponseEntity<>(accountService.getAccountsByCustomerId(customerId), HttpStatus.OK);
//    }

    /**
     * creates bank account for given cusromerId
     *
     * @param accountDto
     * @return response entity
     */
    @PostMapping("/createcustomeraccount")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountDto accountDto) {
        System.out.println("#####" + accountDto.getCustomerId());

        AccountResponse accountDTO = accountService.createCustomerAccount(accountDto);
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
    public ResponseEntity<Account> depositAmount(@PathVariable Long accountNumber, @PathVariable Double amount) {
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
    public ResponseEntity<Account> withdrawAmount(@PathVariable Long accountNumber, @PathVariable Double amount) {
        return new ResponseEntity<>(accountService.withdraw(accountNumber, amount), HttpStatus.OK);
    }

    /**
     * transfer money from one customer to another
     *
     * @param senderAccNo
     * @param receiverAccNo
     * @param amount
     * @return response entity
     */
    @PutMapping("/transfermoney")
    public ResponseEntity<String> transactionBetweenCustomers(@PathParam("senderAccNo") Long senderAccNo,
                                                              @PathParam("receiverAccNo") Long receiverAccNo,
                                                              @PathParam(value = "amount") Double amount) {
        return new ResponseEntity<>(accountService.transactionBetweenCustomers(senderAccNo, receiverAccNo, amount), HttpStatus.OK);
    }

}
