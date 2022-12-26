package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO extends BaseResponse{
    private Integer customerId;
    private String fullName;

    public CustomerDTO() {
        super(true);
    }

    public CustomerDTO(Integer customerId, String fullName) {
        super(true);
        this.customerId=customerId;
        this.fullName =fullName;
    }
}
