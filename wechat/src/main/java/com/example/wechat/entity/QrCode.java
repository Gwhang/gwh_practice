package com.example.wechat.entity;

/**
 * 二维码
 */
public class QrCode {

    //该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
    private long expire_seconds;
    //二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
    private String action_name;
    //二维码详细信息
    private ActionInfo action_info;
//    //场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
//    private String scene_id;
//    //场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
//    private String scene_str;


    public long getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(long expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public ActionInfo getAction_info() {
        return action_info;
    }

    public void setAction_info(ActionInfo action_info) {
        this.action_info = action_info;
    }
}
