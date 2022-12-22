package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.Exception.CustomerNotFoundException;
import com.cognologix.banksystem.dao.CustomerDao;
import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.dto.bank.CustomerListResponse;
import com.cognologix.banksystem.dto.bank.UpdateCustomerDto;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.services.CustomerService;
import com.cognologix.banksystem.services.Implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banksystem/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    CustomerDao customerDao;


    /**
     * Gets customer by id
     *
     * @return reponse entity
     */

    @GetMapping("/getcustomer")
    public ResponseEntity<CustomerListResponse> getCustomer() {
        CustomerListResponse customerListResponse = customerService.getCustomer();
        customerListResponse.setMessage("Customers list");
        HttpStatus httpStatus = customerListResponse.getCustomerList().isEmpty() ? HttpStatus.NOT_FOUND:HttpStatus.FOUND;
        return new ResponseEntity<>(customerListResponse, httpStatus);
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
        HttpStatus httpStatus = customerDTO.getCustomerId() > 0 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(customerDTO, httpStatus );
    }

    @PutMapping("/updatecustomer/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer customerId, @RequestBody UpdateCustomerDto updateCustomerDto){
        CustomerDTO customerDTO = customerService.updateCustomer(customerId, updateCustomerDto);
//        customerDTO.setMessage("Customer details updated successfully");
        HttpStatus httpStatus = customerDTO.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(customerDTO, httpStatus);
    }

}
