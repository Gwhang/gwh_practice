package com.example.sendsms.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.example.sendsms.dao.SendSms;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 发送短信服务的接口实现类
 */
@Service
public class SendSmsImpl implements SendSms {

    /**
     *
     * @param phoneNumber 手机号
     * @param templateCode 模板
     * @param code 参数
     * @return
     */
    @Override
    public boolean send(String phoneNumber, String templateCode, Map<String, Object> code) {
        // 连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<AccessKey>", "<AccessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        // 构建请求
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);//注意这四行，与官网所展示的方法不同
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        // 自定义参数（手机号、验证码、签名、模板）
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",phoneNumber);
        request.putQueryParameter("SignName","流星雨小商城");
        request.putQueryParameter("TemplateCode",templateCode);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code));

        // 发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return false;
    }
}
