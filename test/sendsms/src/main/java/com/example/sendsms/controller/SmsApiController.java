package com.example.sendsms.controller;

import com.example.sendsms.dao.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin//解决跨域
public class SmsApiController {
    @Autowired
    private SendSms sendSms;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/send/{phone}")
    public String code(@PathVariable("phone") String phone){
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return phone + ":" + code + "已存在，还未过期！";
        }
        code = UUID.randomUUID().toString().substring(0,4);
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = sendSms.send(phone, "SMS_204127841", param);
        if(isSend){
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.SECONDS);//TimeUnit可以用来延时或者对时间粒度进行转换;
            return phone + ":" + code + "发送成功！";
        }else{
            return "发送失败！";
        }
    }
}
