package com.example.wechat;

import com.example.wechat.entity.*;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * @ClassName Test
 * @Description:测试类
 * @Author guanWanHang
 * @Date 2020/8/3
 * @Version V1.0
 **/
public class Test {

    @org.junit.Test
    public void testButton(){
        //菜单对象
        Button button = new Button();
        //第一个一级菜单
        button.getButton().add(new ClickButton("1","一级菜单"));
        //第二个菜单
        button.getButton().add(new ViewButton("http://www.baidu.com","一级跳转"));
        //创建第三个菜单
        SubButton sub = new SubButton();
        sub.getSub_button().add(new PhotoOrAlbumButton("图片上传","31"));
        sub.getSub_button().add(new ClickButton("32","点击"));
        sub.getSub_button().add(new ViewButton("http://news.163.com","网易"));
        button.getButton().add(sub);
        String jsonObject=JSONObject.fromObject(button).toString();
        System.out.println(jsonObject);
    }

}
