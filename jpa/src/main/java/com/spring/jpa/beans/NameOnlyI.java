package com.spring.jpa.beans;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author likai
 * @Date 2018/10/24
 */
public interface NameOnlyI {

  String getName();

  @Value("#{'my name is '+target.name}")
  String getMyName();

  @Value("#{args[0] + ' ' + target.name + '!'}")
  String getSalutation(String prefix);
}
