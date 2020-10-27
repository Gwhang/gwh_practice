package com.example.datastructure.demo;

/**
 * 队列测试方法
 */
public class MyQueueTest {

    public static void main(String[] args) {
        //创建一个队列
        MyQueue mq=new MyQueue();
        //入队
        mq.add(9);
        mq.add(8);
        mq.add(7);
        //出队
        System.out.println(mq.poll());
        System.out.println(mq.poll());
        mq.add(6);
        mq.add(5);
        System.out.println(mq.poll());

    }

}
