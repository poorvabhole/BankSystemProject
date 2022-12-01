package com.cognologix.banksystem.services.Implementation;

import com.cognologix.banksystem.Exception.CustomerNotFoundException;
import com.cognologix.banksystem.Exception.EmptyFieldException;
import com.cognologix.banksystem.Exception.EmptyListException;
import com.cognologix.banksystem.dao.CustomerDao;
import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.dto.bank.CustomerListResponse;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Override // create new customer
    public CustomerDTO createCustomer(Customer customer) {
        Customer newCustomer = null;
            newCustomer = customerDao.addCustomer(customer);
        return new CustomerDTO(newCustomer.getCustomerId(),newCustomer.getFullName());
    }

    @Override // get list of registered customer
    public CustomerListResponse getCustomer(){
        List<Customer> customerList = null;
        try {
            customerList = customerDao.findAll();
            if (customerList.size() == 0){
                throw new EmptyListException("Customer information is empty");
            }
        }catch (final EmptyListException exception){
            exception.printStackTrace();
        }

        return new CustomerListResponse(customerList);
    }

    @Override
    public String deleteCustomer(Integer customerId) {
        String message = null;
        try {
            if (customerDao.deleteCustomer(customerId)) {
                message = "Customer deleted";
            }
            else {
                throw new CustomerNotFoundException("Customer not found for given customer Id");
            }
        }catch (final CustomerNotFoundException exception){
            exception.printStackTrace();
        }
        return message;
    }
}
