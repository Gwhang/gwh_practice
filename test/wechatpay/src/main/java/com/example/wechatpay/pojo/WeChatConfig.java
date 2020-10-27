package com.example.wechatpay.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信支付的配置类
 */
@Component
@ConfigurationProperties(prefix="wechat") //接收application.yml中的weChat下面的属性
public class WeChatConfig {

    //微信账户id
    private String appId;

    //微信支付商户号
    private String mcHid;

    //异步接收微信支付结果通知的回调地址
    private String notifyUrl;

    //密钥
    private String wechatPrivateKey;

    //商品描述
    private String body;

    //交易类型
    private String tradeType;

    private String openId;

    //退款结果通知url
    private String notifyRefundUrl;

    //微信支付网关
    private String weChatPrepayUrl;

    //微信退款网关
    private String weChatRefundPrepayUrl;

    //微信付款查询订单网关
    private String weChatQueryPrepayUrl;

    //微信退款查询网关
    private String weChatQueryRefundPrepayUrl;

    //微信浏览器内调起获取openid时的appid
    private String jsapiAppId;

    //JSAPI交易类型
    private String jsapiTradeType;

    //appSecret
    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMcHid() {
        return mcHid;
    }

    public void setMcHid(String mcHid) {
        this.mcHid = mcHid;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getWechatPrivateKey() {
        return wechatPrivateKey;
    }

    public void setWechatPrivateKey(String wechatPrivateKey) {
        this.wechatPrivateKey = wechatPrivateKey;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNotifyRefundUrl() {
        return notifyRefundUrl;
    }

    public void setNotifyRefundUrl(String notifyRefundUrl) {
        this.notifyRefundUrl = notifyRefundUrl;
    }

    public String getWeChatPrepayUrl() {
        return weChatPrepayUrl;
    }

    public void setWeChatPrepayUrl(String weChatPrepayUrl) {
        this.weChatPrepayUrl = weChatPrepayUrl;
    }

    public String getWeChatRefundPrepayUrl() {
        return weChatRefundPrepayUrl;
    }

    public void setWeChatRefundPrepayUrl(String weChatRefundPrepayUrl) {
        this.weChatRefundPrepayUrl = weChatRefundPrepayUrl;
    }

    public String getWeChatQueryPrepayUrl() {
        return weChatQueryPrepayUrl;
    }

    public void setWeChatQueryPrepayUrl(String weChatQueryPrepayUrl) {
        this.weChatQueryPrepayUrl = weChatQueryPrepayUrl;
    }

    public String getWeChatQueryRefundPrepayUrl() {
        return weChatQueryRefundPrepayUrl;
    }

    public void setWeChatQueryRefundPrepayUrl(String weChatQueryRefundPrepayUrl) {
        this.weChatQueryRefundPrepayUrl = weChatQueryRefundPrepayUrl;
    }

    public String getJsapiAppId() {
        return jsapiAppId;
    }

    public void setJsapiAppId(String jsapiAppId) {
        this.jsapiAppId = jsapiAppId;
    }

    public String getJsapiTradeType() {
        return jsapiTradeType;
    }

    public void setJsapiTradeType(String jsapiTradeType) {
        this.jsapiTradeType = jsapiTradeType;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
