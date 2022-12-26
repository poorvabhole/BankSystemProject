package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransferAmountDto extends BaseResponse {
    private Integer senderAccountNo;
    private Integer receiverAccountNo;
    private Double amountTransfer;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime date;

    public TransferAmountDto(Integer senderAccountNo, Integer receiverAccountNo, Double amountTransfer) {
        super(true);
        this.senderAccountNo = senderAccountNo;
        this.receiverAccountNo = receiverAccountNo;
        this.amountTransfer = amountTransfer;
        this.date = LocalDateTime.now();
    }

    public TransferAmountDto() {
        super(true);
    }
}
