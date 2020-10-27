package com.example.demo.test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 代码验证线程池的入队过程以及shutdown之后会不会处理之前提交过的任务
 *
 * 从第4个任务开始入队，直到队列满了之后，又开始创建非核心线程去执行任务
 * （看第14个任务，就会发现线程数变多了，说明增加了一个非核心线程，此时的队列还是满的）
 * 直到第15个任务，也创建了新线程去执行，此时的线程数为5个了，多了两个非核心线程
 *
 * 结论：15个线程都被执行了
 */
public class ThreadTest {

    public static void main(String[] args) {
        ThreadFactory threadFactory=new MyThreadFactory("测试");
        ThreadPoolExecutor executor=new ThreadPoolExecutor(3,6,20,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(10),
                threadFactory,new ThreadPoolExecutor.AbortPolicy());
        for(int i=1; i<=20; i++){
            if(i<=3){
                executor.submit(
                        new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    Thread.sleep(1000);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                System.out.println(Thread.currentThread().getName()+"完成了任务");
                            }
                        }
                );
            }else {
                executor.submit(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(Thread.currentThread().getName()+"完成了任务");
                            }
                        }
                );
                if(i==15){ executor.shutdown(); }
            }
            System.out.println("任务数量"+executor.getTaskCount());
            System.out.println("大约正在执行任务的线程数："+executor.getActiveCount());
            System.out.println("队列："+ executor.getQueue().toString());
            System.out.println("完成任务的数目："+executor.getCompletedTaskCount());
            System.out.println("----------------------------");
        }
    }

}
