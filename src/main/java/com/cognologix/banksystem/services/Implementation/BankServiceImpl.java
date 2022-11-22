package com.cognologix.banksystem.services.Implementation;

import com.cognologix.banksystem.dao.AccountDao;
import com.cognologix.banksystem.dao.BankAccountDao;
import com.cognologix.banksystem.dao.CustomerDao;
import com.cognologix.banksystem.dto.bank.BankAccountDTO;
import com.cognologix.banksystem.dto.bank.DepositResponse;
import com.cognologix.banksystem.entities.Account;
import com.cognologix.banksystem.entities.BankAccount;
import com.cognologix.banksystem.entities.Customer;
import com.cognologix.banksystem.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl  implements BankService {
    @Autowired
    private BankAccountDao bankAccountDao;








//@Override
//    public BaseResponse createAccount(BankAccountDTO account){
////        List<BankAccount> customerList = new ArrayList<>();
////        customerList.add(customer);
//    }

    @Override
    public BankAccountDTO createAccount(BankAccount account) {
//        System.out.println("In service implementation ::"+account.getAccount_type());
        bankAccountDao.save(account);
        return new BankAccountDTO(account.getAccountNumber());

    }
    public DepositResponse deposit(Integer accountNumber, BankAccount account){
        BankAccount bankAccount = bankAccountDao.findById(accountNumber).get();
        Double currentBalance = bankAccount.getBalance();
        currentBalance = currentBalance + account.getBalance();
        bankAccount.setBalance(currentBalance);
        System.out.println();
        bankAccountDao.save(bankAccount);
        return new DepositResponse(bankAccount.getBalance());

    }

//    @Override
//    public CustomerResponse createAccount(@RequestBody Customer customer, @RequestBody String accountType) {
////        return new CustomerResponse(customerDao.save(new Customer(customer,accountType)));
//    }
//
//    @Override
//    public AccountResponse getBalance(Long accountNumber) {
//        return null;
//    }
//
//    @Override
//    public Customer getCustomer(String name) {
//        return null;
//    }
//
//
//    @Override
//    public BaseResponse deposite(Double amount) {
//        return null;
//    }
//
//    @Override
//    public BaseResponse withdrawl(Double amount) {
//        return null;
//    }
}
