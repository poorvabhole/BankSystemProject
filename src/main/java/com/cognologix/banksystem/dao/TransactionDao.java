package com.cognologix.banksystem.dao;

import com.cognologix.banksystem.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT * FROM transaction" +
            " WHERE sender_account_number=?1 OR receiver_account_number=?1", nativeQuery = true)
    List<Transaction> findBySenderAccountNumber(Long senderAccountNumber);
}
