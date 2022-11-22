package com.cognologix.banksystem;

import com.cognologix.banksystem.controller.CustomerController;
import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.entities.Customer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {
//    @Autowired
//    private CustomerController customerController;
//    @Autowired
//    private CustomerDTO customerDTO;
//    Customer customer = new Customer(1, "Poorva Bhole", "Dhule", "12/09/1999","ODNL66513", "214563325325");
//
//    @Test
//    public void createCustomertest(){
//        customerDTO = new CustomerDTO(1,"Poorva Bhole");
////        customerDTO.setCustomerId(1);
////        customerDTO.setFullname("Poorva Bhole");
//        ResponseEntity<CustomerDTO> customerDto = customerController.createCustomer(customer);
//        Assertions.assertNotNull(customerDto.getBody().getCustomerId());
////        Assertions.assertEquals(customerDTO.getFullname(), customerDto.getBody().getFullname());
//    }
////    public void getCustomerTest(){
////
////    }
}
