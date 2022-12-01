package com.cognologix.banksystem.dao;

import com.cognologix.banksystem.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDao extends JpaRepository<Account,Integer> {
    Account findByAccountNumberEquals(Long accountNumber);
    @Query(value = "select * from account where customer_customer_id=?1", nativeQuery = true)
    List<Account> findByCustomerId(Integer customerId);
}
