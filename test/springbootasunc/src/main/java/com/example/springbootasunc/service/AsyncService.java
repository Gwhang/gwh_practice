package com.example.springbootasunc.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步服务
 */
@Service
public class AsyncService {

    // 告诉Spring这是一个异步方法
    @Async
    public void hello() {
        try {
            System.out.println("数据处理中...");
            Thread.sleep(4000);
            System.out.println("数据处理完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
