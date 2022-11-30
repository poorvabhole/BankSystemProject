package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import com.cognologix.banksystem.entities.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountListResponse extends BaseResponse {
    private List<Account> accountsList;
    public AccountListResponse(List<Account> list){
        super(true);
        this.accountsList = list;
    }
}
