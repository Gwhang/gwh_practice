package com.example.demo.test;

/**
 * java 多线程调用接口超时处理
 */
public class JoinDemo {

    public static void main(String[] args) throws Exception{
        Thread method = new Thread(new ThreadMethod());
        method.start();//执行线程
        try {
            method.join(2000);//规定业务接口执行不能超过的时长
        }catch (Exception e){
            e.printStackTrace();
        }
        method.interrupt();//调用中断很重要，如果不调用的话，就会回到上面说的，两个线程并发执行，就起不到效果了。

    }

}

class ThreadMethod implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(3000);//执行业务 改变业务执行时间来测试
            System.out.println("方法调用完成");
        }catch (Exception e){
            System.out.println("方法调用失败");
        }
    }
}
