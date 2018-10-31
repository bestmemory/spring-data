package com.spring.jpa.services;

import com.spring.jpa.beans.Customer;
import javax.security.auth.login.AccountException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author likai
 * @Date 2018/10/26
 */
public interface CustomerService {
    Long addCustomer(Customer customer);

    int deleteCustomer();
}
