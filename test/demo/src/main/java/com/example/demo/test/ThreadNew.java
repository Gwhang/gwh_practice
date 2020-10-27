package com.example.demo.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程的创建方式
 */
public class ThreadNew {
    public static void main(String[] args) {

        new Mythread1().start();

        new Thread(new Mythread2()).start();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Mythread3());
        new Thread(futureTask).start();

        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
//继承Thread类
class Mythread1 extends Thread{
    @Override
    public void run() {
        System.out.println("Mythread1");
    }
}
//实现Runnable接口
class Mythread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("Mythread2");
    }
}
//实现callable接口
class Mythread3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Mythread3");
        return 100;
    }
}