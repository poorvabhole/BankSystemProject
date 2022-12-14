package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import com.cognologix.banksystem.entities.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountStatementResponse extends BaseResponse {
    private List<Transaction> statement;
    public AccountStatementResponse(List<Transaction> statement) {
        super(true);
        this.statement = statement;
    }
}
