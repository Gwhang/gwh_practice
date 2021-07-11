package com.example.demo.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 校验两个集合元素是否完全一致
 */
public class StreamTest12 {

    public static void main(String[] args) {

        List<String> list1 = Arrays.asList("语文","数学","英语");

        List<String> list2 = Arrays.asList("数学","英语","语文");

        // 先排序然后转成string 逗号分隔
        boolean falg = list1.stream().sorted().collect(Collectors.joining()).equals
                (list2.stream().sorted().collect(Collectors.joining()));

        System.out.println(falg);

    }

}
