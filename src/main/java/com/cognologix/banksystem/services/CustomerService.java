package com.cognologix.banksystem.services;

import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.dto.bank.CustomerListResponse;
import com.cognologix.banksystem.entities.Customer;

public interface CustomerService {
    CustomerDTO createCustomer(Customer customer);
    CustomerListResponse getCustomer();
}
