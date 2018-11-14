package com.spring.jpa.beans;

/**
 * @author likai
 * @Date 2018/10/26
 */
public class NameOnly {
  private String name;
  private String address;
  private String aaa;

  public NameOnly(String name, String address) {
    this.name = name;
    this.address = address;
  }

/*
  public NameOnly(String name, String address, String aaa) {
    this.name = name;
    this.address = address;
    this.aaa = aaa;
  }
*/

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAaa() {
    return aaa;
  }

  public void setAaa(String aaa) {
    this.aaa = aaa;
  }
}
