package com.spring.jpa;

import com.spring.jpa.beans.Customer;
import com.spring.jpa.repositories.CustomerRepository;
import com.spring.jpa.services.CustomerService;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private CustomerRepository customerRepository;

  /**
   * save方法会预检查该entity是否持久化，isNew会判断该对象的Id类型 是否实现Persistable或EntityInformation进行
   * 重写isNew方法，如果Id是Number类型，直接判断value==0 true 执行entityManager.persist 否则执行entityManager.merge()
   */
  @Test
  public void insert() {
    Customer customer = new Customer();
    customer.setName("lk");
    customer.setEmail("spring.jpa@163.com");
    customer.setAddress("Shanghai PuDong Area XueYe Road");
    customer.setPhone("13699999999");
    //这里保存以后customer的id会被填充为保存后entity的id
    Customer savedEntity = customerRepository.save(customer);
    //保存并立即刷新数据库，由于customer以及提供id，会执行merge方法进行保存
//    Customer savedAndFlush = customerRepository.saveAndFlush(customer);
    List<Customer> batchCustomers = Arrays.asList(new Customer(), new Customer());
    //批量保存,saveAll是循环单挑插入，并不是batch操作，数据较大使用时请注意性能
//    List<Customer> batchSaves = customerRepository.saveAll(batchCustomers);
  }

  /**
   * 删除操作，除了batch操作，其他方法均先查询后删除
   */
  @Test
  public void delete() {
    Customer customer = new Customer();
    customer.setId(Long.valueOf(42L));
    //select * from customer where id=?;delete from customer where id=?;
    customerService.deleteCustomer();
    //同delete(entity)
//    customerRepository.deleteById(38L);
    //select * from customer;循环遍历id单个删除...delete from customer where id=?...
//    customerRepository.deleteAll();
//    Customer customerOther = new Customer();
//    customerOther.setId(41L);
//    List<Customer> deleteAll = Arrays.asList(customer,customerOther);
    //循环执行delete(entity)
//    customerRepository.deleteAll(deleteAll);
    //不查询直接：delete from customer;(风险较大清空表)
//    customerRepository.deleteAllInBatch();
    //不查询直接：delete from customer where id=? or id=?
//    customerRepository.deleteInBatch(deleteAll);
  }

  @Test
  @Transactional
  public void query() {

    //select * from customer;
//    customerRepository.findAll();
    //select * from customer order by name desc;
//    customerRepository.findAll(Sort.by(Direction.DESC, "name"));
    //select * from customer limit 0,10;
//    customerRepository.findAll(PageRequest.of(0, 10));
    //select * from customer where address = "address";
//    customerRepository.findCustomerByAddress("address");
//    Customer customer = new Customer();
//    customer.setAddress("address");
//    customer.setName("lk");
//    customer.setPhone("133");
    //select * from customer where address ="address";
//    customerRepository.findAll(Example.of(customer));
//    ExampleMatcher matcher = ExampleMatcher.matching()
//        .withMatcher("name", match -> match.contains())
//        .withMatcher("phone", match -> match.startsWith());
    //select * from customer where name like '%lk%" and phone like '133%' and address = "address";
//    customerRepository.findOne(Example.of(customer,matcher));
    //namedQuery:select * from customer where name = "lk";
//    customerRepository.findByNameNQ("lk");
    //select * from customer where name like '%k';
//    customerRepository.findCustomersNameLike("k");
    //select * from customer where name like 'k'; 如果需要模糊查询需要手动拼接 % 连接符
//    customerRepository.findCustomersByNameLike("k");
    //select * from customer where name like "%l";
//    customerRepository.findCustomersByNameStartingWith("l");
    //select * from customer where name like "%k%";
//    customerRepository.findCustomersByNameContains("k");
    //select * from customer where name = "lk" and phone = "133";
//    customerRepository.findCustomersNameAndPhone("133","lk");
//   int count = customerRepository.modifyByPhone("ddsdssd");
   int count = customerRepository.deleteCustomer();
    System.out.println(count);
  }

  @Test
  public void page() {
    customerRepository.findAll(new PageRequest(1, 2));
  }

}
