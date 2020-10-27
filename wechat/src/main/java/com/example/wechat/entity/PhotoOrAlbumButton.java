package com.example.wechat.entity;

import com.google.common.collect.Lists;

import java.io.PrintWriter;
import java.util.List;

/**
 * @ClassName PhotoOrAlbumButton
 * @Description:拍照或者相册发图
 * @Author guanWanHang
 * @Date 2020/7/27
 * @Version V1.0
 **/
public class PhotoOrAlbumButton extends AbstractButton {

    private String type="pic_photo_or_album";
    private String key;
    List<SubButton> sub_button= Lists.newArrayList();

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

    public List<SubButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<SubButton> sub_button) {
        this.sub_button = sub_button;
    }

    public PhotoOrAlbumButton(String name, String key, List<SubButton> sub_button) {
        super(name);
        this.key = key;
        this.sub_button = sub_button;
    }
    public PhotoOrAlbumButton(String name, String key) {
        super(name);
        this.key = key;
    }

}
