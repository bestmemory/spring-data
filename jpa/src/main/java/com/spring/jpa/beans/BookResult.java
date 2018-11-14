package com.spring.jpa.beans;

/**
 * @author likai
 * @Date 2018/11/13
 */
public class BookResult {

  private Long id;
  private String name;
  private String size;


  public BookResult(Long id, String name, String size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

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

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }
}
