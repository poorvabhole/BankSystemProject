package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.dto.bank.AccountDto;
import com.cognologix.banksystem.dto.bank.AccountListResponse;
import com.cognologix.banksystem.dto.bank.AccountResponse;
import com.cognologix.banksystem.dto.bank.AccountStatementResponse;
import com.cognologix.banksystem.dto.bank.DeactivateAccountResponse;
import com.cognologix.banksystem.dto.bank.TransactionDto;
import com.cognologix.banksystem.dto.bank.TransferAmountDto;
import com.cognologix.banksystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/banksystem/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * Gets all accounts
     *
     * @return account list response
     */
    @GetMapping("/getaccount")
    public ResponseEntity<AccountListResponse> getAccounts() {
        AccountListResponse response = accountService.getAccounts();
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

        HttpStatus httpStatus = response.getAccountsList().isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.FOUND;

        return new ResponseEntity<>(response, httpStatus);
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
        HttpStatus httpStatus = transactionDto.getTransactionId() == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(transactionDto, httpStatus);
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
        HttpStatus httpStatus = transactionDto.getTransactionId() == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(transactionDto, httpStatus);
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
        HttpStatus httpStatus = transferAmountDto.getAmountTransfer() == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;

        return new ResponseEntity<>(transferAmountDto, httpStatus);
    }

    @DeleteMapping("/deactivateaccount")
    public ResponseEntity<DeactivateAccountResponse> deactivateAccount(@PathParam("accountNumber") Integer accountNumber){
        DeactivateAccountResponse response = accountService.deactivateAccount(accountNumber);
        response.setMessage("Account deactivated successfully");
        HttpStatus httpStatus = response.getAccountNumber() > 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response,httpStatus);
    }
}
