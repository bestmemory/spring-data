package com.spring.jpa.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author likai
 * @Date 2018/11/9
 */
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Integer count;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "detail_id",referencedColumnName = "id")
  private BookDetail bookDetail;

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

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BookDetail getBookDetail() {
    return bookDetail;
  }

  public void setBookDetail(BookDetail bookDetail) {
    this.bookDetail = bookDetail;
  }
}
