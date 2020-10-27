package com.example.sendsms.dao;

import java.util.Map;

/**
 * 编辑发送短信接口
 */
public interface SendSms {
    /**
     * 发送短信
     * @param phoneNumber 手机号
     * @param templateCode 模板
     * @param code 参数
     * @return
     */
   public boolean send(String phoneNumber, String templateCode, Map<String, Object> code);

}
