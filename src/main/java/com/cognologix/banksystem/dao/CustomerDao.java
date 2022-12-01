package com.cognologix.banksystem.dao;

import com.cognologix.banksystem.entities.Customer;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerDao {
    private Customer customer;

    private AccountDao accountDao;
    List<Customer> customerList = new ArrayList<>();
    Integer id = 0;

    public Customer addCustomer(Customer newCustomer) {

        newCustomer.setCustomerId(++id);
        customerList.add(newCustomer);
        return newCustomer;
    }

    public List<Customer> findAll() {
        return customerList;
    }

    public boolean deleteCustomer(Integer customerId) {
        List<Customer> list = customerList.stream().filter(customer1 -> customer1.getCustomerId() == customerId).collect(Collectors.toList());
        boolean isDelete = false;
        if (list.size() != 0) {
            customerList.remove(list.get(0));
            isDelete = accountDao.deleleByCustomerId(customerId);
            return isDelete;
        } else {
            return isDelete;
        }
    }
}
