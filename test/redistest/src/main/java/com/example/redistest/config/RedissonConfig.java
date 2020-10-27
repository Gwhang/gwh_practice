package com.example.redistest.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redis连接
 */
@Configuration//表示改类为配置类
public class RedissonConfig {

    public static final String NODE="redis://127.0.0.1:6379";

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(NODE);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

}
