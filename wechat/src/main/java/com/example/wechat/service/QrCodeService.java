package com.example.wechat.service;

import com.example.wechat.entity.ActionInfo;
import com.example.wechat.entity.QrCode;
import com.example.wechat.utils.HttpUtil;
import com.example.wechat.utils.SendMsg;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;

/**
 * 生成二维码
 */
@Service
public class QrCodeService {

    /**
     * 生成二维码
     * @return
     */
    public String qrCode(String msg){
        String token=WechatService.getAccessToken();
        String url= SendMsg.QRCODE_CREATE.replace("TOKEN",token);
        QrCode code=new QrCode();
        code.setExpire_seconds(6000);
        code.setAction_name("QR_STR_SCENE");
        ActionInfo actionInfo=new ActionInfo();
        actionInfo.setScene_str(msg);
        code.setAction_info(actionInfo);
        String result= HttpUtil.post(url, JSONObject.fromObject(code).toString());
        System.out.println(result);
        JSONObject jsonObject= JSONObject.fromObject(result);
        return String.valueOf(jsonObject.get("ticket"));
    }

}
