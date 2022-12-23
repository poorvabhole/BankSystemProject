package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeactivateAccountResponse extends BaseResponse {
    private Integer accountNumber;
    private String accountStatus;

    public DeactivateAccountResponse() {
        super(true);
    }

    public DeactivateAccountResponse(boolean success, Integer accountNumber, String accountStatus) {
        super(success);
        this.accountNumber = accountNumber;
        this.accountStatus = accountStatus;
    }
}
