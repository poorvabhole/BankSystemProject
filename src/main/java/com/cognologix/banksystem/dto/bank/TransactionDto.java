package com.cognologix.banksystem.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Integer transactionId;
    private String type;
    private Double balance;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh/mm/ss")
    private LocalDateTime date;
}
