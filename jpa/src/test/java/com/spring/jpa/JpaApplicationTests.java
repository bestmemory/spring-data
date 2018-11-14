package com.spring.jpa;

import com.spring.jpa.beans.Customer;
import com.spring.jpa.beans.CustomerGroup;
import com.spring.jpa.beans.NameOnly;
import com.spring.jpa.beans.NameOnlyI;
import com.spring.jpa.repositories.BookRepository;
import com.spring.jpa.repositories.CustomerRepository;
import com.spring.jpa.repositories.GroupRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private GroupRepository groupRepository;

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
    //定义关联关系后，确定级联关系，新增有可能会级联
//    CustomerGroup customerGroup = new CustomerGroup();
//    customerGroup.setType("33");
//    customer.setCustomerGroup(customerGroup);

    //这里保存以后customer的id会被填充为保存后entity的id
    Customer savedEntity = customerRepository.save(customer);
    //保存并立即刷新数据库，由于customer以及提供id，会执行merge方法进行保存
    Customer savedAndFlush = customerRepository.saveAndFlush(customer);
    List<Customer> batchCustomers = Arrays.asList(new Customer(), new Customer());
    //批量保存,saveAll是循环单挑插入，并不是batch操作，数据较大使用时请注意性能
    List<Customer> batchSaves = customerRepository.saveAll(batchCustomers);
  }

  /**
   * 删除操作，除了batch操作，其他方法均先查询后删除
   */
  @Test
  public void delete() {
    //select * from customer where id=?;delete from customer where id=?;
    //同delete(entity)
    customerRepository.deleteById(38L);
    //select * from customer;循环遍历id单个删除...delete from customer where id=?...
    customerRepository.deleteAll();
    Customer customer = new Customer();
    customer.setId(Long.valueOf(42L));
    Customer customerOther = new Customer();
    customerOther.setId(41L);
    List<Customer> deleteAll = Arrays.asList(customer, customerOther);
    //循环执行delete(entity)
    customerRepository.deleteAll(deleteAll);
    //不查询直接：delete from customer;(风险较大清空表)
    customerRepository.deleteAllInBatch();
    //不查询直接：delete from customer where id=? or id=?
    customerRepository.deleteInBatch(deleteAll);
  }

  @Test
  @Transactional
  public void query() {
    customerRepository.findByNameSql("lk1");
    //select * from customer;
    customerRepository.findAll();
    //select * from customer where id = 1;
    customerRepository.findById(1L);
    //select * from customer where address = "address";
    customerRepository.findCustomerByAddress("address");
    //select * from customer where name = "lk" and phone = "133";
    customerRepository.findCustomersNameAndPhone("133", "lk");
    //select * from customer where name like '%k';
    customerRepository.findCustomersNameLike("k");
    //select * from customer where name like 'k'; 如果需要模糊查询需要手动拼接 % 连接符
    customerRepository.findCustomersByNameLike("k");
    //select * from customer where name like "%l";
    customerRepository.findCustomersByNameStartingWith("l");
    //select * from customer where name like "%k%";
    customerRepository.findCustomersByNameContains("k");

    //select * from customer order by name desc;
    customerRepository.findAll(Sort.by(Direction.DESC, "name"));
    //select * from customer limit 0,10;
    customerRepository.findAll(PageRequest.of(0, 10));

    Customer customer = new Customer();
    customer.setAddress("address");
    //select * from customer where address ="address";
    customerRepository.findAll(Example.of(customer));
    customer.setName("lk");
    customer.setPhone("133");
    ExampleMatcher matcher = ExampleMatcher.matching()
        .withMatcher("name", match -> match.contains())
        .withMatcher("phone", match -> match.startsWith());
    //select * from customer where name like '%lk%" and phone like '133%' and address = "address";
    customerRepository.findOne(Example.of(customer, matcher));
    //namedQuery:select * from customer where name = "lk";
    customerRepository.findByNameNQ("lk");

    //update customer set name = "myPhone" where id = 1;
    customerRepository.modifyByPhone("myPhone", 1L);
    //delete from customer where id = 2;
    customerRepository.deleteCustomer(2L);

    //基于接口的返回值
    List<NameOnlyI> interfaces = customerRepository.findCustomersByName("lk1");
    //基于类的返回值，如果有两个构造函数会报错，无法解析转换
    List<NameOnly> nameOnlies = customerRepository.findByName("lk1");
    //基于类的返回值，@Query显式声明返回bean
    List<NameOnly> objs = customerRepository.findByName4Obj("lk1");
    //@Query返回map 用as做key，不用as默认key是0，1，2...
    List<Map<String, Object>> maps = customerRepository.findByName4Map("lk1");
  }

  @Test
  public void testJoin() {
    //one2one
    bookRepository.findByName("语文");
    bookRepository.findByNameAndBookDetail_Id("语文",2L);
    bookRepository.findResults();
  }
}
