package com.spring.jpa.repositories;

import com.spring.jpa.beans.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author likai
 * @Date 2018/10/24
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>,
    PagingAndSortingRepository<Customer, Long> {

  List<Customer> findCustomerByAddress(String address);

  List<Customer> findByNameNQ(String name);

  @Query("select c from Customer c where name like %?1")
  List<Customer> findCustomersNameLike(String name);

  List<Customer> findCustomersByNameLike(String name);

  List<Customer> findCustomersByNameStartingWith(String name);
  List<Customer> findCustomersByNameContains(String name);

  @Query("select c from Customer c where name = :name and phone = :phone")
  List<Customer> findCustomersNameAndPhone(@Param("phone") String phone,
      @Param("name") String name);

  @Modifying
  @Query("update Customer c set c.name = :name where c.id =1")
  int modifyByPhone(@Param("name") String name);

  @Modifying
  @Query("delete from Customer c  where c.id =2")
  int deleteCustomer();

}
