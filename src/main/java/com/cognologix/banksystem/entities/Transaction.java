package com.cognologix.banksystem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "transactionId")
    private Integer transactionId;

    @Column(name = "senderAccountNumber")
    private Integer senderAccountNumber;

    @Column(name = "receiverAccountNumber")
    private Integer receiverAccountNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "withdrawAmount")
    private Double withdrawAmount;

    @Column(name = "depositAmount")
    private Double depositAmount;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "date")
    private LocalDateTime date;

    public Transaction(Integer senderAccountNumber, Integer receiverAccountNumber, String type,
                       Double balance, Double withdrawAmount, Double depositAmount, LocalDateTime date) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.type = type;
        this.balance = balance;
        this.withdrawAmount = withdrawAmount;
        this.depositAmount = depositAmount;
        this.date = date;
    }
}
