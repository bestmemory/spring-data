package com.spring.jpa.services.impl;

import com.spring.jpa.beans.Customer;
import com.spring.jpa.repositories.CustomerRepository;
import com.spring.jpa.services.CustomerService;
import javax.security.auth.login.AccountException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author likai
 * @Date 2018/10/26
 */
@Service
public class CustomerServiceImpl implements CustomerService {
  private CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  @Transactional
  public Long addCustomer(Customer customer)  {
    Customer customer1 = new Customer();
    customer1.setName("lk1");
    customer1.setEmail("spring.jpa@163.com");
    customer1.setAddress("Shanghai PuDong Area XueYe Road");
    customer1.setPhone("13688888888");
    Customer customer2 = new Customer();
    customer2.setName("lk2");
    customer2.setEmail("spring.jpa@163.com");
    customer2.setAddress("Shanghai PuDong Area XueYe Road");
    customer2.setPhone("136888dsssssssss888888888");
//    Customer savedAndFlushE = customerRepository.saveAndFlush(customer1);
    Customer savedEntity = customerRepository.save(customer2);
    if (1==1) {
      throw new RuntimeException("s");
    }
    return 1L;
  }

  @Override
  @Transactional
  public int deleteCustomer() {
    return customerRepository.deleteCustomer();
  }
}
