package com.cognologix.banksystem.services.Implementation;

import com.cognologix.banksystem.Exception.CustomerNotFoundException;
import com.cognologix.banksystem.Exception.EmptyListException;
import com.cognologix.banksystem.dao.CustomerDao;
import com.cognologix.banksystem.dto.bank.CustomerDTO;
import com.cognologix.banksystem.dto.bank.CustomerListResponse;
import com.cognologix.banksystem.dto.bank.UpdateCustomerDto;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.entities.ErrorEnum;
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
        Customer newCustomer = customerDao.save(customer);
        return new CustomerDTO(newCustomer.getCustomerId(), newCustomer.getFullName());
    }

    @Override // get list of registered customer
    public CustomerListResponse getCustomer() {
        List<Customer> customerList = null;
        try {
            customerList = customerDao.findAll();
            if (customerList.size() == 0) {
                throw new EmptyListException(ErrorEnum.EMPTY_LIST.getMessage());
            }
        } catch (final EmptyListException exception) {
            throw new EmptyListException(exception.getMessage());
        }
        return new CustomerListResponse(customerList);
    }

    @Override
    public CustomerDTO updateCustomer(Integer customerId, UpdateCustomerDto updateCustomerDto) {
        Customer customer;
        try {
            customer = customerDao.findByCustomerId(customerId);
            if (customer == null){
                throw new CustomerNotFoundException(ErrorEnum.CUSTOMER_NOT_FOUND.getMessage());
            }
            customer.setFullName(updateCustomerDto.getFullName());
            customer.setAddress(updateCustomerDto.getAddress());
            customer.setDateOfBirth(updateCustomerDto.getDateOfBirth());
            customerDao.save(customer);

        }catch (final CustomerNotFoundException exception){
            throw new CustomerNotFoundException(exception.getMessage());
        }
        return new CustomerDTO(customer.getCustomerId(),customer.getFullName());
    }
}
