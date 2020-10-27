package com.example.wechat.entity;

/**
 * @ClassName ViewButton
 * @Description:类描述
 * @Author guanWanHang
 * @Date 2020/7/27
 * @Version V1.0
 **/
public class ViewButton extends AbstractButton{

    private String type="view";
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ViewButton(String url,String name) {
        super(name);
        this.url = url;

    }


}
