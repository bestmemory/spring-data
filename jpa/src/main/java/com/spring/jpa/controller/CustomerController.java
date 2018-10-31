package com.spring.jpa.controller;

import com.spring.jpa.services.CustomerService;
import javax.security.auth.login.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author likai
 * @Date 2018/10/26
 */
@RestController
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping("/save")
  public Long saveCustomer()  {
    return customerService.addCustomer(null);
  }

  @PostMapping("/delete")
  public int delete()  {
    return customerService.deleteCustomer();
  }
}
