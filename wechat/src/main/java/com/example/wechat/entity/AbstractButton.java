package com.example.wechat.entity;

/**
 * @ClassName AbstractButton
 * @Description:类描述
 * @Author guanWanHang
 * @Date 2020/7/27
 * @Version V1.0
 **/
public class AbstractButton {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractButton(String name) {
        this.name = name;
    }

    public AbstractButton() {
    }
}
