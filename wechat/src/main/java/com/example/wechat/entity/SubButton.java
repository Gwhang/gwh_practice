package com.example.wechat.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @ClassName SubButton
 * @Description:类描述
 * @Author guanWanHang
 * @Date 2020/7/27
 * @Version V1.0
 **/
public class SubButton extends AbstractButton{

    private List<AbstractButton> sub_button= Lists.newArrayList();

    public List<AbstractButton> getSub_button() {
        return sub_button;
    }

    public void setSubButton(List<AbstractButton> sub_button) {
        this.sub_button = sub_button;
    }

    public SubButton() {
    }

    public SubButton(String name, List<AbstractButton> sub_button) {
        super(name);
        this.sub_button = sub_button;
    }
    public SubButton(String name) {
        super(name);
    }

    public SubButton(List<AbstractButton> sub_button) {
        this.sub_button = sub_button;
    }
}
