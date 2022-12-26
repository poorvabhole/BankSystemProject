package com.cognologix.banksystem.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

//    private Long accountNumber;
    private String accountType;
    private Double balance;
    private Integer customerId;
}
