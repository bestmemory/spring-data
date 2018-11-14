package com.spring.jpa.repositories;

import com.spring.jpa.beans.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author likai
 * @Date 2018/11/13
 */
public interface BookDetailRepository extends JpaRepository<BookDetail,Long> {

}
