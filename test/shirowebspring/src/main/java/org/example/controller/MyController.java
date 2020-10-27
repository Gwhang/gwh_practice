package org.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器
 */
@Controller
public class MyController {

    @RequestMapping("/doLogin")
    public String doLogin(String username,String password){
        System.out.println("登录");
        //1.shiro认证
        Subject subject = SecurityUtils.getSubject();
        //加密
        password = new Md5Hash(password, username, 2014).toString();
        System.out.println(password);
        //2.把用户输入的用户名和密码封装成一个usernamePasswordToken对象
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //3.登录  查找，执行认证方法
        subject.login(token);

        System.out.println(subject.isAuthenticated()?"登录成功":"登录失败");
        return "success";
    }

}
