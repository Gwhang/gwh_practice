package com.example.datastructure.demo.linear;

import com.example.datastructure.demo.linear.utils.MyArray;

import java.util.Arrays;

/**
 * 面向对象数组
 */
public class ArrayTest4 {

    public static void main(String[] args) {
        MyArray arr=new MyArray();
        System.out.println("数组长度:"+arr.getSize());
        arr.show();
        //往数组中添加一个元素
        arr.add(1);
        arr.add(2);
        arr.add(3);
        System.out.println("数组长度:"+arr.getSize());
        arr.show();
        arr.delete(1);
        arr.show();
        System.out.println("获取指定下标的元素:"+arr.getIndex(1));
        System.out.println("---------------------------------------");
        arr.insert(1,2);
        arr.show();
        System.out.println("---------------------------------------");
        arr.set(0,0);
        arr.show();
        System.out.println("--------------------------------------");
        System.out.println(arr.linearSeart(2));
        System.out.println("--------------------------------------");
        System.out.println(arr.binarySeart(9));

    }


}
