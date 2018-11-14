package com.spring.jpa.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.NamedQuery;

/**
 * @author likai
 * @Date 2018/10/24
 */
@Entity
@NamedQuery(name = "Customer.findByNameNQ", query = "select c from Customer c where name =?1")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String address;
  private String phone;

//  //targetEntity 默认是关联实体，若使用接口作为关联实体时，应指明targetEntity的实现类，且接口应继承Serializable，否则无法被解析
//  @ManyToOne(fetch = FetchType.EAGER,targetEntity = CustomerGroup.class)
//  @JoinColumn(name = "group_type",referencedColumnName = "type")
//  private CustomerGroupInterface customerGroup;

  //manyToOne 单向关联或 oneToMany双向关联
//  //这里使用非主键作为外键关联，type在customer_group表中唯一约束，也可以使用@JoinTable 处理关联表做连接这里不再演示
  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name = "group_type",referencedColumnName = "type")
  private CustomerGroup customerGroup;

  //customerGroup为一的一方，单向关联customer（多方）
//  @Column(name = "group_type")
//  private String type;



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public CustomerGroup getCustomerGroup() {
    return customerGroup;
  }

  public void setCustomerGroup(CustomerGroup customerGroup) {
    this.customerGroup = customerGroup;
  }


//  public String getType() {
//    return type;
//  }
//
//  public void setType(String type) {
//    this.type = type;
//  }
}
