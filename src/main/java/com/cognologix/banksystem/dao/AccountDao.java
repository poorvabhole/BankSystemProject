package com.cognologix.banksystem.dao;

import com.cognologix.banksystem.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account,Integer> {
    Account findByAccountNumberEquals(Long accountNumber);
}
