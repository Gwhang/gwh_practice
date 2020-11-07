package com.example.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * EnableAutoConfiguration注解:SpringBoot会将所有依赖，比如：数据库，redis,spring等等，统统以一个自动化的配置加载到工程中
 */
@SpringBootApplication(scanBasePackages ="com.example.seckill")//扫描本地包
@MapperScan("com.example.seckill.dao")//扫描dao包
@ServletComponentScan // 启动类必须加上@ServletComponentScan才会加载过滤器
public class SeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }

}
