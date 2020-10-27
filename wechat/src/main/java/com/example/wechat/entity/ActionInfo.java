package com.example.wechat.entity;

/**
 * 二维码信息 此处String 为例子
 */
public class ActionInfo {

    //场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
    private String scene_str;

    public String getScene_str() {
        return scene_str;
    }

    public void setScene_str(String scene_str) {
        this.scene_str = scene_str;
    }
}
