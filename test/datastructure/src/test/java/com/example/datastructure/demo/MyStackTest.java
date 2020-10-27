package com.example.datastructure.demo;

//栈测试类
public class MyStackTest {

    public static void main(String[] args) {
        //创建一个栈
        MyStack ms=new MyStack();
       // System.out.println(ms.pop());
        //压入数据
        ms.push(9);
        ms.push(8);
        ms.push(7);
//        System.out.println(ms.pop());
//        System.out.println(ms.pop());
//        System.out.println(ms.pop());
        System.out.println(ms.peek());

    }

}
