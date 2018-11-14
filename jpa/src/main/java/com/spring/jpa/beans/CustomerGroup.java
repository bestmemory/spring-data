package com.spring.jpa.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author likai
 * @Date 2018/11/9
 */
@Entity
@Table(name = "customer_group")
public class CustomerGroup implements CustomerGroupInterface {

  private static final long serialVersionUID = -6956725658881048590L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String type;
  private String name;
  private Integer level;

  //oneToMany 单向关联，customer中无需注解，注意joinColumn是table中的name
//  @OneToMany(fetch = FetchType.EAGER)
//  @JoinColumn(name = "group_type")

  //oneToMany 双向关联，customer中无需注解,mappedBy必须指向entity中的属性名，即标有@ManyToOne的属性名，且此处不可再使用@JoinColumn。
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerGroup")
  private List<Customer> customers;
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

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }
}
