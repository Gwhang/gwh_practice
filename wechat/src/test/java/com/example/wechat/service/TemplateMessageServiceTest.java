package com.example.wechat.service;

import com.example.wechat.entity.Template;
import com.example.wechat.entity.TemplateData;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 模板消息测试类
 */
public class TemplateMessageServiceTest {

    public TemplateMessageService templateMessageService=new TemplateMessageService();

    /**
     * 设置行业信息
     */
    @Test
    public void setIndustry() {
        templateMessageService.setIndustry();
    }

    /**
     * 设置所属行业信息
     */
    @Test
    public void getIndustry(){
        templateMessageService.getIndustry();
    }

    /**
     * 获取模板消息列表
     */
    @Test
    public void gteTemplateList(){
        templateMessageService.gteTemplateList();
    }

    /**
     * 发送模板消息
     */
    @Test
    public void sendTemplate(){
        Template template=new Template();
        template.setTouser("ojpF7jsGGt07TjaU8d-JqESEudfI");
        template.setTemplate_id("892koXAPwwx7y03LcYhr6oFyFqy8gHJXT1iXJZJEq4g");
        template.setUrl("www.baidu.com");
        template.setColor("#173177");
        Map<String,TemplateData> dataMap= Maps.newHashMap();
        TemplateData first=new TemplateData();
        first.setValue("崽崽芮消费支出");
        dataMap.put("first",first);
        TemplateData pay=new TemplateData();
        pay.setValue("6300");
        pay.setColor("#173177");
        dataMap.put("pay",pay);
        TemplateData type = new TemplateData();
        type.setValue("办公用品");
        dataMap.put("type",type);
        TemplateData describe = new TemplateData();
        describe.setValue("于2020年8月30号购买华为Pad消费用款");
        dataMap.put("describe",describe);
        template.setData(dataMap);
        System.out.println(JSONObject.fromObject(template).toString());
        templateMessageService.sendTemplate(template);
    }

}