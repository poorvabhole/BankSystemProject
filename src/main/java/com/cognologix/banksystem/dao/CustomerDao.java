package com.cognologix.banksystem.dao;

import com.cognologix.banksystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Customer findByCustomerId(Integer customerId);
}
