package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.dto.bank.AccountDto;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.dto.bank.AccountResponse;
import com.cognologix.banksystem.dto.bank.AccountStatementResponse;
import com.cognologix.banksystem.dto.bank.TransactionDto;
import com.cognologix.banksystem.dto.bank.TransferAmountDto;
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

    /**
     * Get account statement
     *
     * @param accountNumber
     * @return account statement response
     * */
    @GetMapping("/getstatement")
    public ResponseEntity<AccountStatementResponse> getStatement(@PathParam("accountNumber") Integer accountNumber) {
        AccountStatementResponse response = accountService.getAccountStatement(accountNumber);
        response.setMessage("Account statement");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Gets accounts according to customer id
     *
     * @param customerId
     * @return account list response
     */
    @GetMapping("/customeraccounts/{customerId}")
    public ResponseEntity<AccountListResponse> accountsByCustomerId(@PathVariable Integer customerId) {
        AccountListResponse response = accountService.getAccountsByCustomerId(customerId);
        return new ResponseEntity<>(accountService.getAccountsByCustomerId(customerId), HttpStatus.OK);
    }

    /**
     * creates bank account for given cusromerId
     *
     * @param accountDto
     * @return response entity
     */
    @PostMapping("/createcustomeraccount")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountDto accountDto) {
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
    public ResponseEntity<TransactionDto> depositAmount(@PathVariable Integer accountNumber, @PathVariable Double amount) {
        TransactionDto transactionDto = accountService.deposit(accountNumber, amount);
        transactionDto.setMessage("Amount credited successfully");
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    /**
     * withdraw amount
     *
     * @param accountNumber
     * @param amount
     * @return reaponse entity
     */
    @PutMapping("/withdrawAmount/{accountNumber}/{amount}")
    public ResponseEntity<TransactionDto> withdrawAmount(@PathVariable Integer accountNumber, @PathVariable Double amount) {
        TransactionDto transactionDto = accountService.withdraw(accountNumber, amount);
        transactionDto.setMessage("Amount debited successfully");
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
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
    public ResponseEntity<TransferAmountDto> transactionBetweenCustomers(@PathParam("senderAccNo") Integer senderAccNo,
                                                                         @PathParam("receiverAccNo") Integer receiverAccNo,
                                                                         @PathParam(value = "amount") Double amount) {
        TransferAmountDto transferAmountDto = accountService.transactionBetweenCustomers(senderAccNo, receiverAccNo, amount);
        transferAmountDto.setMessage("Amount is transfer successfully..");
        return new ResponseEntity<>(transferAmountDto, HttpStatus.OK);
    }

    @DeleteMapping("/deactivateaccount")
    public ResponseEntity<String> deactivateAccount(@PathParam("accountNumber") Integer accountNumber){
        return new ResponseEntity<>(accountService.deactivateAccount(accountNumber),HttpStatus.OK);
    }
}
