package com.example.wechat.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @ClassName Button
 * @Description:菜单类
 * @Author guanWanHang
 * @Date 2020/7/27
 * @Version V1.0
 **/
public class Button {

    private List<AbstractButton> button= Lists.newArrayList();

    public List<AbstractButton> getButton() {
        return button;
    }

    public void setButton(List<AbstractButton> button) {
        this.button = button;
    }


}
