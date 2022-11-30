package com.cognologix.banksystem.entities;

import lombok.*;
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
    private Long senderAccountNumber;

    @Column(name = "receiverAccountNumber")
    private Long receiverAccountNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "balance")
    private Double balance;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh/mm/ss")
    @Column(name = "date")
    private LocalDateTime date;
}
