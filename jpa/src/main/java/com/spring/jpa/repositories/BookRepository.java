package com.spring.jpa.repositories;

import com.spring.jpa.beans.Book;
import com.spring.jpa.beans.BookResult;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author likai
 * @Date 2018/11/13
 */
public interface BookRepository extends JpaRepository<Book, Long> {

  //可以查出关联表的实体，结果是根据两次sql查询出来的，即select * from book where name = ?;->查出detail_id->select * from book_detail where id = ?;
  List<Book> findByName(String name);
  //可以根据关联表的属性作为条件查询，结果同样是根据两次sql查询出来的。
  List<Book> findByNameAndBookDetail_Id(String name, Long id);

  //自定义返回值，一次查询返回结果
  @Query("select new com.spring.jpa.beans.BookResult(b.id as id,b.name as name ,d.size as size) from Book as b left join BookDetail as d on b.bookDetail = d.id")
  List<BookResult> findResults();

}
