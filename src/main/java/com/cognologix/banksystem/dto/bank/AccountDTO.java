package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import com.cognologix.banksystem.entities.Account;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor
//@AllArgsConstructor
public class AccountDTO extends BaseResponse{
    private String accountId = UUID.randomUUID().toString();
    private String accountType;
    private Double balance = 500.00;
    private Integer customerId;

    public AccountDTO(Account newAccount) {
//        super(true);
        this.accountId = newAccount.getAccountId();
        this.accountType = newAccount.getAccountType();
        this.balance = newAccount.getBalance();
        this.customerId = newAccount.getCustomerId();
    }
//    private Double balance;

//    public AccountDTO(Account account){
//        this.balance = account.getBalance();
//    }
//    public AccountDTO(boolean success) {
//        super(success);
//    }
}
