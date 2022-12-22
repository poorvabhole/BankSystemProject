package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;

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
