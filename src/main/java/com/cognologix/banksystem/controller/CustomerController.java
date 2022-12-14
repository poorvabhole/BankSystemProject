package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.dto.bank.CustomerListResponse;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.services.Implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banksystem/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    /**
     * Gets customer by id
     *
     * @return reponse entity
     */

    @GetMapping("/getcustomer")
    public ResponseEntity<CustomerListResponse> getCustomer() {
        CustomerListResponse customerListResponse = customerService.getCustomer();
        customerListResponse.setMessage("Customers list");
        return new ResponseEntity<>(customerListResponse, HttpStatus.OK);
    }

    /**
     * Register/ create customer
     *
     * @param customer details
     * @return response entity
     */
    @PostMapping("/register")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody Customer customer) {
        CustomerDTO customerDTO = customerService.createCustomer(customer);
        customerDTO.setMessage("Customer added successfully");
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

}
