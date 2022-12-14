package com.cognologix.banksystem.dto.bank;

import com.cognologix.banksystem.dto.BaseResponse;
import com.cognologix.banksystem.entities.Customer;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CustomerListResponse extends BaseResponse {
    private List<Customer> customerList;
    public CustomerListResponse(List<Customer> customers){
        super(true);
        this.customerList = customers;
    }
}
