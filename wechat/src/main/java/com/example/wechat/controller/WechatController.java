package com.example.wechat.controller;

import com.example.wechat.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @ClassName WechatController
 * @Description:微信接入类
 * @Author guanWanHang
 * @Date 2020/7/25
 * @Version V1.0
 **/
@RestController
public class WechatController {



    @Autowired
    private WechatService wechatService;

    /**
    *@MethodName: weixin
    *@Description: TODO
    *@Param: * @param request:
     * @param response:
    *@Return: * @return: void
    *@Author: guanwanhang
    *@Date: 2020/7/25
    **/
    @RequestMapping(value = "/weixin", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public void initWeixinURL(HttpServletRequest request, HttpServletResponse response) throws Exception{

        /* 请求接入携带参数
         * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
         * timestamp	时间戳
         * nonce	随机数
         * echostr	随机字符串
        */
        String signature= request.getParameter("signature");
        String timestamp= request.getParameter("timestamp");
        String nonce= request.getParameter("nonce");
        String echostr= request.getParameter("echostr");

        //1）将token、timestamp、nonce三个参数进行字典序排序
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        //验证签名
        if(wechatService.check(timestamp,nonce,signature)){
            //请原样返回echostr参数内容
            PrintWriter printWriter=response.getWriter();
            printWriter.print(echostr);
            printWriter.flush();
            printWriter.close();
        }else{
            System.out.println("接入失败");
        }


    }
    /**
    *@MethodName: replyMessage
    *@Description: 接收消息和事件推送
    *@Param: * @param request:
    *@Return: * @return: java.lang.String
    *@Author: guanwanhang
    *@Date: 2020/7/25
    **/
    @RequestMapping(value = "/weixin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String replyMessage(HttpServletRequest request) throws Exception{
        //处理消息和事件推送
       Map<String,String> resultMap=wechatService.parseRequest(request.getInputStream());
        System.out.println(resultMap);
        //处理消息事件和消息回复
        String msgType=wechatService.getRespose(resultMap);
       return msgType;
    }

}
