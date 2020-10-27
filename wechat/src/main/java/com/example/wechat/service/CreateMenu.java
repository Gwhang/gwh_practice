package com.example.wechat.service;

import com.example.wechat.entity.*;
import com.example.wechat.utils.HttpUtil;
import com.example.wechat.utils.SendMsg;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName CreateMenu
 * @Description:创建菜单
 * @Author guanWanHang
 * @Date 2020/8/3
 * @Version V1.0
 **/
public class CreateMenu {

    public static void main(String[] args) {
        //菜单对象
        Button button = new Button();
        //第一个一级菜单
        button.getButton().add(new ClickButton("1","一级菜单"));
        //第二个菜单
        button.getButton().add(new ViewButton("http://www.baidu.com","一级跳转"));
        //创建第三个菜单
        SubButton sub = new SubButton("有子菜单");
        sub.getSub_button().add(new PhotoOrAlbumButton("图片上传","31"));
        sub.getSub_button().add(new ClickButton("32","点击"));
        sub.getSub_button().add(new ViewButton("http://news.163.com","网易"));
        button.getButton().add(sub);
        String data= JSONObject.fromObject(button).toString();
        String url= SendMsg.MENU.replace("ACCESS_TOKEN",WechatService.getAccessToken());
        System.out.println(data);
        System.out.println(WechatService.getAccessToken());
        System.out.println(url);
        HttpUtil.post(url,data);
    }

}
