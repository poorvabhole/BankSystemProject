package com.cognologix.banksystem;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
//    @Autowired
//    private CustomerServiceImpl customerService;
//    @Autowired
//    private CustomerDaoold customerDao;
//    Customer customer = new Customer(1, "Poorva Bhole", "Dhule", "12/09/1999","ODNL66513", "214563325325");
//
//    @Test
//    public void createCustomerTest(){
////        Customer customerAcutal = new Customer(1, "Poorva Bhole", "Dhule", "12/09/1999", 214563325);
//        Customer customerAcutal = new Customer();
//        customerAcutal.setCustomerId(1);
//        customerAcutal.setFullName("poorva bhole");
//        customerAcutal.setAddress("Dhule");
//        customerAcutal.setDateOfBirh("12/09/1999");
//        customerAcutal.setPanNumber("ODNL66513");
//        customerAcutal.setAadharNumber("214563325325");
//        Assert.assertNotNull(customerService.createCustomer(customerAcutal));
////        Assertions.assertEquals(customer,customerAcutal);
//        Assertions.assertEquals(customer.getAadharNumber(),customerAcutal.getAadharNumber());
//        Assertions.assertEquals(customer.getPanNumber(),customerAcutal.getPanNumber());
//    }
//    @Test
//    public void getCustomer_emptyListExceptionTest(){
//        customerDao.addCustomer(customer);
//        CustomerListResponse customerList = customerService.getCustomer();
//        if (customerList.getCustomerList().size() != 0){
//            Assert.assertNotNull(customerList);
//        }else {
//            throw new EmptyListException("Customer information is empty");
//        }
//    }
}
