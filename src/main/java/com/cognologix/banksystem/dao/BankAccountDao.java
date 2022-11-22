package com.cognologix.banksystem.dao;

import com.cognologix.banksystem.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountDao extends JpaRepository<BankAccount,Integer> {

}
