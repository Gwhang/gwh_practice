package com.example.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * list 去重
 */
public class ArrayListExample {

    public static void main(String[] args) {

        List<Integer> numberList=new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(numberList);
        // 1.LinkedHashSet是在一个ArrayList删除重复数据的最佳方法。LinkedHashSet在内部完成两件事：删除重复数据,保持添加到其中的数据的顺序
        LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>(numberList);
        System.out.println(linkedHashSet);
        // 2.使用java8新特性stream进行List去重
        List<Integer> collect = numberList.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
        // 3.利用List的contains方法循环遍历,重新排序,只添加一次数据,避免重复
        List<Integer> list = new ArrayList<Integer>();
        for (Integer number:numberList){
            if(!list.contains(number)){
                list.add(number);
            }
        }
        System.out.println(list);
    }

}
