package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.Exception.EmptyListException;
import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.dto.bank.CustomerListResponse;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.services.Implementation.BankServiceImpl;
import com.cognologix.banksystem.services.Implementation.CustomerServiceImpl;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    /**
     * Gets customer by id
     * @return reponse entity
     * */

    @GetMapping("/getcustomer")
    public ResponseEntity<CustomerListResponse> getCustomer(){
        CustomerListResponse customerListResponse = customerService.getCustomer();
        return new ResponseEntity<>(customerListResponse, HttpStatus.OK);
    }

    /**
     * Register/ create customer
     *
     * @param customer details
     * @return response entity
     * */
    @PostMapping("/register")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody Customer customer){
        CustomerDTO customerDTO = customerService.createCustomer(customer);
        customerDTO.setMessage("Customer added successfully");
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    /**
     *
     * */
    @DeleteMapping("/deletecustomer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId){
        return new ResponseEntity<>(customerService.deleteCustomer(customerId),HttpStatus.OK);
    }

}
