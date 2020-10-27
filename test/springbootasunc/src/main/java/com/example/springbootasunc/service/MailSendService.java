package com.example.springbootasunc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件发送服务
 */
@Service
public class MailSendService {

    /**
     * 邮件服务注入
     */
    @Autowired
    JavaMailSender mailSender;


    /**
     * 简单邮件
     */
    public void sendSimpleMail() {
        // 1.新建一个新邮件
        SimpleMailMessage message = new SimpleMailMessage();

        // 2.设置邮件内容
        message.setSubject("通知-邮件发送");
        message.setText("好好学习，天天向上");

        // 3.发送邮件
        message.setFrom("123456@qq.com");
        message.setTo("123456@qq.com");
        mailSender.send(message);
    }

    /**
     * 复杂邮件
     */
    public void sendComplexMail() throws MessagingException {
        // 1.创建复杂邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // 2.设置邮件内容
        helper.setSubject("通知-邮件测试");
        helper.setText("<p style='color:red'>好好学习，天天向上</p>", true);

        // 3.添加邮件附件（注意这里要改成你自己的文件路径）
        helper.addAttachment("文件附件.txt", new File("D:\\Users\\QY\\Desktop\\问题.txt"));
        helper.addAttachment("图片.jpg", new File("D:\\Users\\QY\\Desktop\\1.jpg"));

        // 4.发送邮件
        helper.setFrom("123456@qq.com");
        helper.setTo("123456@qq.com");
        mailSender.send(mimeMessage);
    }

}
