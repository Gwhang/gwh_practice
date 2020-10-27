package com.example.wechat.utils;

/**
 * @ClassName sendMsg
 * @Description:消息事件回复类型
 * @Author guanWanHang
 * @Date 2020/7/25
 * @Version V1.0
 **/
public class SendMsg {
    //文本消息
    public static final String TEXT = "text";
    //图片消息
    public static final String IMAGE = "image";
    //语音消息
    public static final String VOICE = "voice";
    //视频消息
    public static final String VIDEO = "video";
    //音乐消息
    public static final String MUSIC = "music";
    //图文消息
    public static final String NEWS = "news";
    //事件
    public static final String EVENT = "event";
    //事件 点击
    public static final String EVENT_CLICK = "CLICK";
    //事件 跳转
    public static final String EVENT_VIEW = "VIEW";
    //事件 带参二维码 subscribe
     public static final String SUBSCRIBE="subscribe";
    //事件 扫码
    public static final String EVENT_SCANCODE_PUSH = "scancode_push";
    //事件 提示
    public static final String EVENT_SCANCODE_WAITMSG = "scancode_waitmsg";
    //事件 弹出系统拍照发图
    public static final String EVENT_PIC_SYSPHOTO = "pic_sysphoto";

    //设置所属行业
    public static final String API_SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    //获取所属行业
    public static final String GET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    //获取模板ID
    public static final String API_ADD_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
    //获取模板消息列表
    public static final String GET_ALL_PRIVATE_TEMPLATE=" https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
    //删除模板消息
    public static final String DEL_PRIVATE_TEMPLATE="https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";
    //发送模板消息
    public static final String SEND_TEMPLATE= "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    //上传素材
    public static final String UPLOAD="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    //生成二维码
    public static final String QRCODE_CREATE="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    //换取二维码
    public static final String QRCODE_TICKET ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    //获取用户信息
    public static final String USER_INFO ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    //ACCESS_TOKEN
    public static final String ACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //menu
    public static final String MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";




}
