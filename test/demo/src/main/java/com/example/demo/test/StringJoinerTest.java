package com.example.demo.test;

import java.util.StringJoiner;

/**
 *
 * StringJoiner是Java8新出的一个类，用于构造由分隔符分隔的字符序列，
 * 并可选择性地从提供的前缀开始和以提供的后缀结尾。省的我们开发人员再次通过StringBuffer或者StingBuilder拼接。
 *
 */
public class StringJoinerTest {

    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(",");
        sj.add("A").add("B").add("C").add("D").add("E");
        System.out.println(sj.toString());
        StringJoiner sj2 = new StringJoiner(",","[","]");
        sj2.add("A").add("B").add("C").add("D").add("E");
        System.out.println(sj2.toString());
    }

}
