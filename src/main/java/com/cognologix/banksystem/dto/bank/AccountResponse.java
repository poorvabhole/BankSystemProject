package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import com.cognologix.banksystem.entities.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse extends BaseResponse{
    private Integer accountNumber;
    private String accountType;
    private Double balance;

    public AccountResponse(Account newAccount) {
        super(true);
        this.accountNumber = newAccount.getAccountNumber();
        this.accountType = newAccount.getAccountType();
        this.balance = newAccount.getBalance();
    }

    public AccountResponse(boolean success) {
        super(true);
    }
}
