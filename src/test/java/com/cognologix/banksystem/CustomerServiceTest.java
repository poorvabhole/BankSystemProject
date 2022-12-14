package com.cognologix.banksystem;

import com.cognologix.banksystem.Exception.EmptyListException;
import com.cognologix.banksystem.dao.CustomerDao;
import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.services.Implementation.CustomerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CustomerServiceTest.class})
public class CustomerServiceTest {
    @Mock
    CustomerDao customerDao;

    @InjectMocks
    CustomerServiceImpl customerService;

    Customer firstCustomer = new Customer(1, "Poorva Bhole", "Pune", "12/09/1999", "ODNL66513", "214563325325");
    Customer secondCustomer = new Customer(2, "Kaustubh Bhole", "Dhule", "12/02/1995", "DBPM6876", "2145354418");

    @Test
    public void createCustomerTest() {
        when(customerDao.save(firstCustomer)).thenReturn(firstCustomer);
        CustomerDTO customerDTO = customerService.createCustomer(firstCustomer);
        Assertions.assertEquals("Poorva Bhole", customerDTO.getFullname());
    }

    @Test
    public void getAllCustomerTest() {
        List<Customer> customers = new ArrayList<>();
        customers.add(firstCustomer);
        customers.add(secondCustomer);
        customerDao.saveAll(customers);

        when(customerDao.findAll()).thenReturn(customers);
        Assertions.assertEquals(2, customerService.getCustomer().getCustomerList().size());
    }

    @Test
    public void getCustomer_emptyListException() {
        List<Customer> customers = new ArrayList<>();
        customerDao.saveAll(customers);
        when(customerDao.findAll()).thenReturn(customers);
        Exception exception = Assertions.assertThrows(EmptyListException.class, () -> {
            customerService.getCustomer();
        });
        String expectedMessage = "Customer information is empty";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
