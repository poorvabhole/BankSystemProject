package com.cognologix.banksystem;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
//    @Autowired
//    CustomerDaoold customerDao;
//    @Autowired
//    private AccountServiceImpl accountService;
//    @Autowired
//    private AccountDaoasAS accountDao;
//    Customer customer = new Customer(1, "Poorva Bhole", "Dhule", "12/09/1999","ODNL66513", "214563325325");
//
//    Account account = new Account("are6587-687-65874","current", 1500.00,1);
//
//    @Test
//    public void createAccountTest(){
//        Account accountActual = new Account();
//        accountActual.setAccountId("are6587-687-65874");
//        accountActual.setAccountType("current");
//        accountActual.setBalance(1500.00);
//        accountActual.setCustomerId(1);
//        Assertions.assertEquals(account.getAccountId(),accountActual.getAccountId());
//    }
//
////    @Test
////    public void getAccount_emptyListExceptionTest(){
////        customerDao.addCustomer(customer);
////        accountDao.createAccount(1,account);
////        AccountListResponse accountList = accountService.getAccount();
////        if (accountList.size() != 0){
////            Assertions.assertNotNull(accountList);
////        }else {
////        throw new EmptyListException("Account list is empty");
////        }
////    }
//    @Test
//    public void depositAmount_validateAccountNumber(){
//        customerDao.addCustomer(customer);
//        accountDao.createAccount(1,account);
//        Account updatedAccount = accountService.deposit("are6587-687-65874",500.00);
//        Assertions.assertEquals(account.getAccountId(),updatedAccount.getAccountId());
////        Assertions.assertNotNull();
//    }
//    @Test
//    public void withdrawAmount_validateAccountNumber(){
//        customerDao.addCustomer(customer);
//        accountDao.createAccount(1,account);
//        Account updatedAccount = accountService.withdraw("are6587-687-65874",000.00);
//        Assertions.assertEquals(account.getAccountId(),updatedAccount.getAccountId());
//    }
//
//    @Test
//    public void insufficeintBalanceExceptionTest(){
//        customerDao.addCustomer(customer);
//        accountDao.createAccount(1,account);
////        Account updatedAccount = accountService.withdraw("are6587-687-65874",1500.00);
//        Exception exception = Assertions.assertThrows(InsufficentBalanceException.class,() ->{
//            accountService.withdraw("are6587-687-65874",1500.00);
//        });
//        String expectedMessage = "Insufficent balance, can not withdraw money";
//        String actualMessage = exception.getMessage();
//        Assertions.assertEquals(expectedMessage,actualMessage);
//    }
//
}
