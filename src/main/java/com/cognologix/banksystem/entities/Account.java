package com.cognologix.banksystem.entities;

import com.cognologix.banksystem.dto.bank.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
//    @TableGenerator(name = "numberGenerator",initialValue = 10000, allocationSize = 10)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "accountNumber")
    private Integer accountNumber;

    @Column(name = "accountStatus")
    private String accountStatus = "Active";

    @Column(name = "accountType")
    @NotEmpty(message = "Required field")
    private String accountType;

    @Column(name = "balance")
    @NotNull(message = "Required field")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "customer_customer_id", nullable = false)
    private Customer customer;

    public Account(AccountDto accountDto) {
        this.accountType = accountDto.getAccountType();
        this.setBalance(accountDto.getBalance());
    }
}
