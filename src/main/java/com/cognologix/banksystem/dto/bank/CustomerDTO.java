package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import lombok.*;

@Getter
@Setter
public class CustomerDTO extends BaseResponse{
    private Integer customerId;
    private String fullname;

    public CustomerDTO(Integer customerId, String fullName) {
        super(true);
        this.setCustomerId(customerId);
        this.setFullname(fullName);
    }
}
