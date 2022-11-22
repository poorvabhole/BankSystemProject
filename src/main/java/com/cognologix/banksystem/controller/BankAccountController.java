package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.Exception.AccountCreationException;
import com.cognologix.banksystem.dto.bank.BankAccountDTO;
import com.cognologix.banksystem.dto.bank.DepositResponse;
import com.cognologix.banksystem.entities.BankAccount;
import com.cognologix.banksystem.services.Implementation.BankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {
    @Autowired
    private BankServiceImpl bankService;

    @PostMapping("/createaccount")
    public ResponseEntity<BankAccountDTO> createAccount(@RequestBody BankAccount account){
//        BaseResponse baseResponse = null;
        BankAccountDTO response =this.bankService.createAccount(account);
//        baseResponse.setMessage("Account has been created");
//        return new ResponseEntity<>(response,HttpStatus.OK);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/deposit/{accountNumber}")
    public ResponseEntity<DepositResponse> deposit(@PathVariable Integer accountNumber, @RequestBody BankAccount account){
        DepositResponse response = this.bankService.deposit(accountNumber,account);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
