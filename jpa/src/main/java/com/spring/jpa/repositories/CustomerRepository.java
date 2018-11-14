package com.spring.jpa.repositories;

import com.spring.jpa.beans.Customer;
import com.spring.jpa.beans.NameOnly;
import com.spring.jpa.beans.NameOnlyI;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author likai
 * @Date 2018/10/24
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>,
    PagingAndSortingRepository<Customer, Long> {

  List<Customer> findCustomerByAddress(String address);

  List<Customer> findCustomersByNameLike(String name);

  List<Customer> findCustomersByNameStartingWith(String name);

  List<Customer> findCustomersByNameContains(String name);

  @Query("select c from Customer c where name like %?1")
  List<Customer> findCustomersNameLike(String name);

  @Query(value = "select * from customer where name =?1",nativeQuery = true)
  List<Customer> findByNameSql(String name);

  @Query("select c from Customer c where name = :name and phone = :phone")
  List<Customer> findCustomersNameAndPhone(@Param("phone") String phone,
      @Param("name") String name);

  List<Customer> findByNameNQ(String name);

  @Modifying
  @Query("update Customer c set c.name = :name where c.id = :id")
  int modifyByPhone(@Param("name") String name,@Param("id") Long id);

  @Modifying
  @Query("delete from Customer c  where c.id = ?1")
  int deleteCustomer(Long id);

  List<NameOnlyI> findCustomersByName(String name);

  List<NameOnly> findByName(String name);

  @Query("select new com.spring.jpa.beans.NameOnly(name,address) from Customer where name = ?1")
  List<NameOnly> findByName4Obj(String name);

  @Query("select new map(name as myname,address as myaddress) from Customer where name = :name")
  List<Map<String, Object>> findByName4Map(@Param("name") String name);


//  List<NameOnly> findCustomersByCustomerGroup_Name(String name);
}
