package com.spring.jpa.beans;

/**
 * @author likai
 * @Date 2018/10/24
 */
public class User {
  private String name;

  public User() {
  }

  public User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
