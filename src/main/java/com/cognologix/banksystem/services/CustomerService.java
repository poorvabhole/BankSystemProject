package com.cognologix.banksystem.services;

import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.dto.bank.CustomerListResponse;
import com.cognologix.banksystem.dto.bank.UpdateCustomerDto;
import com.cognologix.banksystem.entities.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerDTO createCustomer(Customer customer);
    CustomerListResponse getCustomer();
    CustomerDTO updateCustomer(Integer customerId, UpdateCustomerDto updateCustomerDto);
}
