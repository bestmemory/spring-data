package com.spring.jpa.repositories;

import com.spring.jpa.beans.CustomerGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author likai
 * @Date 2018/11/11
 */
public interface GroupRepository extends JpaRepository<CustomerGroup,Long> {

}
