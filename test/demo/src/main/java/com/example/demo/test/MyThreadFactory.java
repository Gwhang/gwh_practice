package com.example.demo.test;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 代码验证线程池的入队过程以及shutdown之后会不会处理之前提交过的任务
 * 验证场景:
 * 创建一个核心线程数为3，最大线程数为6，阻塞队列为10的线程池
 * 前三个任务丢进去，让他睡1s才开始执行，之后的任务正常执行。当第十五个任务进去执行后，调用shutdown关闭线程池。
 *
 */
public class MyThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId=new AtomicInteger(1);//AtomicInteger原子操作类 保证数据的原子性 多数使用在多线程并发情况下

    public MyThreadFactory(String featureOfGroup) {
        this.namePrefix = featureOfGroup+",线程编号-";
    }


    @Override
    public Thread newThread(Runnable r) {
        String name=namePrefix+nextId.getAndIncrement();
        Thread thread=new Thread(null,r,name,0);
        return thread;
    }
}
