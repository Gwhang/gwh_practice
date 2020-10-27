package com.example.wechat.service;

import com.example.wechat.entity.Industry;
import com.example.wechat.entity.Template;
import com.example.wechat.entity.TemplateIdShort;
import com.example.wechat.utils.SendMsg;
import com.example.wechat.utils.HttpUtil;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信模板消息服务
 */
@Service
public class TemplateMessageService {

    @Autowired
    private WechatService wechatService;

    /**
     * 设置模板消息
     * @return
     */
    public String setIndustry(){
        //获取accessToken
        String token=WechatService.getAccessToken();
        String url = SendMsg.API_SET_INDUSTRY.replace("ACCESS_TOKEN",token);
        //注意设置的行业信息不是乱写的，是按照微信公众号提供的文档查询的 https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Template_Message_Interface.html
        Industry industry=new Industry();
        industry.setIndustry_id1("1");
        industry.setIndustry_id2("4");
       String result = HttpUtil.post(url, JSONObject.fromObject(industry).toString());
        System.out.println(result);
        return result;
    }

    /**
     * 获取设置的行业信息
     */
    public String getIndustry(){
        //获取accessToken
        String token=WechatService.getAccessToken();
        String url = SendMsg.GET_INDUSTRY.replace("ACCESS_TOKEN",token);
        String result=HttpUtil.get(url);
        System.out.println(result);
        return result;
    }

    /**
     * 获取模板ID
     * template_id_short 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     */
    public String apiAddTemplate(String template_id_short){
        String token=WechatService.getAccessToken();
        String url = SendMsg.API_ADD_TEMPLATE.replace("ACCESS_TOKEN",token);
        TemplateIdShort templateIdShort=new TemplateIdShort();
        templateIdShort.setTemplate_id_short(template_id_short);
        String result = HttpUtil.post(url, JSONObject.fromObject(templateIdShort).toString());
        System.out.println(result);
        return result;
    }
    /**
     * 获取模板消息列表
     *
     */
    public String gteTemplateList(){
        //获取accessToken
        String token=WechatService.getAccessToken();
        String url = SendMsg.GET_ALL_PRIVATE_TEMPLATE.replace("ACCESS_TOKEN",token);
        String result=HttpUtil.get(url);
        System.out.println(result);
        return result;
    }
    /**
     * 删除模板
     */
    public String delTemplate(String template_id){
        Map<String,String> map= Maps.newHashMap();
        map.put("template_id",template_id);
        String token=WechatService.getAccessToken();
        String url = SendMsg.DEL_PRIVATE_TEMPLATE.replace("ACCESS_TOKEN",token);
        String result=HttpUtil.post(url,JSONObject.fromObject(map).toString());
        System.out.println(result);
        return result;
    }

    /**
     * 发送模板消息
     */
    public String sendTemplate(Template template){
        String token=WechatService.getAccessToken();
        String url = SendMsg.SEND_TEMPLATE.replace("ACCESS_TOKEN",token);
        String result = HttpUtil.post(url, JSONObject.fromObject(template).toString());
        System.out.println(result);
        return result;
    }


}
