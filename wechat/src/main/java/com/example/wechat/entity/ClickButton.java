package com.example.wechat.entity;

/**
 * @ClassName ClickButton
 * @Description:类描述
 * @Author guanWanHang
 * @Date 2020/7/27
 * @Version V1.0
 **/
public class ClickButton extends AbstractButton {

    private String type="click";

    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ClickButton(String key,String name) {
        super(name);
        this.key = key;

    }
}
