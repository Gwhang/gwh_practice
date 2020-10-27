package com.example.springbootasunc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务测试服务
 */
@Service
public class ScheduledService {

    // cron表达式：秒 分 时 日 月 周几
    @Scheduled(cron = "0/2 * * * * *")
    public void hello() {
        System.out.println("执行定时任务~");
    }

}
