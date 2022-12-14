package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import com.cognologix.banksystem.entities.Transaction;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDto extends BaseResponse {
    private Integer transactionId;
    private String type;
    private Double depositAmount;
    private Double withdrawAmount;
    private Double balance;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime date;

    public TransactionDto(Transaction transaction) {
        super(true);
        this.transactionId = transaction.getTransactionId();
        this.type = transaction.getType();
        this.depositAmount = transaction.getDepositAmount();
        this.withdrawAmount = transaction.getWithdrawAmount();
        this.balance = transaction.getBalance();
        this.date = transaction.getDate();
    }
}
