package com.cognologix.banksystem.controller;

import com.cognologix.banksystem.dao.CustomerDao;
import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.dto.bank.CustomerListResponse;
import com.cognologix.banksystem.dto.bank.UpdateCustomerDto;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerDao customerDao;

    @MockBean
    private CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getCustomerTest() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");

        List<Customer> customerList = List.of(customer);
        customerDao.saveAll(customerList);
        CustomerListResponse listResponse = new CustomerListResponse(customerList);
        when(customerService.getCustomer()).thenReturn(listResponse);
        mockMvc.perform(get("/banksystem/customer/getcustomer"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.customerList.size()", is(listResponse.getCustomerList().size())));
    }

    @Test
    void getCustomer_CustomerNotFoundExceptionTest() throws Exception {
        List<Customer> list = new ArrayList<>();
        CustomerListResponse listResponse = new CustomerListResponse(list);
        when(customerService.getCustomer()).thenReturn(listResponse);
        mockMvc.perform(get("/banksystem/customer/getcustomer")
                        .content(objectMapper.writeValueAsString(null))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createCustomerTest() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");
        customer.setAddress("Pune");
        customer.setDateOfBirth("12/09/1999");
        customer.setPanNumber("afeg54654");
        customer.setAadharNumber("564985651");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(1);
        customerDTO.setFullName("Poorva");
        when(customerService.createCustomer(customer)).thenReturn(customerDTO);

        mockMvc.perform(post("/banksystem/customer/register")
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId", is(customerDTO.getCustomerId())));
    }

    @Test
    public void updateCustomerTest() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFullName("Poorva");
        customer.setAddress("Pune");
        customer.setDateOfBirth("12/09/1999");

        UpdateCustomerDto updateCustomerDto = new UpdateCustomerDto("Purva", "Dhule", "15/8/1997");

        CustomerDTO customerDTO = new CustomerDTO(1, "Purva");

        when(customerService.updateCustomer(1, updateCustomerDto)).thenReturn(customerDTO);

        mockMvc.perform(put("/banksystem/customer/updatecustomer/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk());
    }
}