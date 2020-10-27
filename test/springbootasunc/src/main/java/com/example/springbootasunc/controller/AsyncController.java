package com.example.springbootasunc.controller;

import com.example.springbootasunc.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建异步测试控制层
 */
@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/hello")
    public String hello() {
        //调用异步方法
        asyncService.hello();
        return "OK!";
    }

}
