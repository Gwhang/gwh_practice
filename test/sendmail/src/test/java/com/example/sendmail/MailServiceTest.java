package com.example.sendmail;

import com.example.sendmail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.naming.*;


/**
 * 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    //注入模板
    @Resource
    private TemplateEngine templateEngine;

    /**
     * 测试文本文件
     */
   @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("hang_15236270334@163.com","测试邮箱","文本邮件发送");
    }

    /**
     * 发送HTML文件
     */
    @Test
    public void sendHtmlMailTest() throws Exception{
        String content="<html>\n" +
                "<boby>\n" +
                "<h3>这是一封HTML文件</h3>\n" +
                "</boby>\n" +
                "</html>";

        mailService.sendHtmlMail("hang_15236270334@163.com","测试HTML邮件",content);
    }

    /**
     * 发送附件
     */
    @Test
    public void sendAttachmentsMail() throws Exception{
        String filePath="C:/Users/hang_/Desktop/【华夏典当行】交接文档(2)(1).docx";
        mailService.sendAttachmentsMail("hang_15236270334@163.com","测试带附件邮件","这是一封带附件的邮件",filePath);
    }

    /**
     * 发送图片邮件
     * @throws Exception
     */
    @Test
    public void sendInlinResourceMailTest() throws Exception{
        String rscPath="D:\\1.png";
        String rscId="neo001";
        String content = "<HTML>\n" +
                "<BOBY> 这是带图片的邮件:<img src=\'cid:"+rscId+"\'>" +
                "</img></BOBY></HTML>";
        mailService.sendInlinResourceMail("hang_15236270334@163.com","测试图片邮件",content,rscPath,rscId);

    }

    /**
     * 模板邮件发送
     * @throws Exception
     */
    @Test
    public void testTmeplateMailTest() throws Exception{
        //获取模板
        Context context=new Context();
        //设置模板参数
        context.setVariable("1d","4183");
        //读取模板内容 模板名称
        String emailContent=templateEngine.process("emailTemplates",context);

        mailService.sendHtmlMail("hang_15236270334@163.com","模板邮件",emailContent);

    }



}
