package com.example.wechat.service;

import com.example.wechat.utils.HttpUtil;
import com.example.wechat.utils.SendMsg;
import org.springframework.stereotype.Service;

/**
 * 获取用户信息
 */
@Service
public class UserInfoService {

    public String getUserInfo(String openId){
        String token=WechatService.getAccessToken();
        String url= SendMsg.USER_INFO.replace("ACCESS_TOKEN",token).replace("OPENID",openId);
        String result= HttpUtil.get(url);
        System.out.println(result);
        return result;
    }

}
