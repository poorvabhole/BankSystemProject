package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import com.cognologix.banksystem.entities.Account;

import java.util.List;

public class AccountListResponse extends BaseResponse {
    private List<Account> accountsList;
    public AccountListResponse(List<Account> list){
        super(true);
        this.accountsList = list;
    }
}
