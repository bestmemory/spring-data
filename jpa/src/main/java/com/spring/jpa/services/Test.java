package com.spring.jpa.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author likai
 * @Date 2018/10/29
 */
public class Test {
  static int a;
  int b;
  static int c;
  public int aMethod() {
    a++;
    return a;
  }
  public int bMethod() {
    b++;
    return b;
  }
  public static int cMethod() {
    System.out.println("static method c");
    c++;
    return c;
  }
  public static void main(String[] args) {
    List<String> strs = Arrays.asList("a","ab","c","d").stream().filter(str->str.startsWith("a")).collect(
        Collectors.toList());
    strs.forEach(str-> System.out.println(str));
//    Test test=new Test();
//    test.aMethod();
//    System.out.println(test.aMethod());
//
//    Test test2=new Test();
//    test2.bMethod();
//    System.out.println(test2.bMethod());
//
//    Test test3=new Test();
//    test3.cMethod();
//    System.out.println(test3.cMethod());
//    System.out.println(test3.aMethod());
//    System.out.println(test3.bMethod());
  }
}
