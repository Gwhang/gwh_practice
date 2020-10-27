package com.example.springbootasunc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailSendServiceTest {

    @Autowired
    MailSendService mailSendService;

    @Test
    void contextLoads() throws MessagingException {
        // 发送简单邮件
        mailSendService.sendSimpleMail();
        // 发送复杂邮件
        mailSendService.sendComplexMail();
    }
}