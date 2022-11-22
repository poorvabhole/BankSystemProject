package com.cognologix.banksystem.entities;

import com.cognologix.banksystem.dto.bank.BankAccountDTO;
import lombok.*;


import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountNumber;
    @Column
    private String accountHolderName;
    @Column
    private String panNumber;
    @Column
    private Integer aadharNumber;
    @Column
    private String address;
    @Column
    private String accountType;
    @Column
    private Double balance = 500.00;

//    public BankAccount(BankAccountDTO account) {
//        this.setAccountHolderName(account.getAccountHolderName());
//        this.setPanNumber(account.getPanNumber());
//        this.setAadharNumber(account.getAadharNumber());
//        this.setAddress(account.getAddress());
//        this.setAccountType(account.getAccount_type());
//        this.setBalance(account.getBalance());
//    }
}
