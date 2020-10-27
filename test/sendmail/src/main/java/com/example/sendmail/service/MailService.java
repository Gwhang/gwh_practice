package com.example.sendmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 发送邮件服务层
 */
@Service
public class MailService {

    //发送人
    @Value("${spring.mail.username}")
    private String formName;

    //注入发送邮件服务
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送文本邮件方法
     * @param toName 发送给谁
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String toName,String subject,String content){
        //创建邮件对象
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(toName);//发送给谁
        message.setSubject(subject);//邮件主题
        message.setText(content);//邮件内容
        message.setFrom(formName);//发送人
        mailSender.send(message);
    }

    /**
     * 发送HTML邮件
     * @param toName
     * @param subject
     * @param content
     * @throws Exception
     */
    public void sendHtmlMail(String toName,String subject,String content) throws Exception{
        //创建邮箱对象
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(formName);//发送人
        helper.setTo(toName);//接收人
        helper.setSubject(subject);//邮件主题
        helper.setText(content,true);//邮件内容
        mailSender.send(message);

    }

    /**
     * 发送附件邮件
     * @param toName
     * @param subject
     * @param content
     * @param filePath 文件路径
     */
    public void sendAttachmentsMail(String toName,String subject,String content,String filePath) throws Exception{

        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(formName);
        helper.setTo(toName);
        helper.setSubject(subject);
        helper.setText(content,true);
        //读取文件流
        FileSystemResource file=new FileSystemResource(new File(filePath));
        String fileName=file.getFilename();
        //设置附件
        helper.addAttachment(fileName,file);
        helper.addAttachment(fileName+"test",file);//支持多附件
        mailSender.send(message);

    }

    /**
     * 发送图片邮件
     * @param toName
     * @param subject
     * @param content
     * @param rscPath 图片地址
     * @param rscId
     */
    public void sendInlinResourceMail(String toName,String subject,String content,
                                      String rscPath,String rscId) throws Exception{

        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(formName);
        helper.setTo(toName);
        helper.setSubject(subject);
        helper.setText(content,true);

        //读取文件流
        FileSystemResource res=new FileSystemResource(new File(rscPath));
        helper.addInline(rscId,res);

        mailSender.send(message);

    }

}
