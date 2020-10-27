package com.example.wechat.entity;

/**
 * @ClassName AccessToken
 * @Description:token
 * @Author guanWanHang
 * @Date 2020/7/26
 * @Version V1.0
 **/
public class AccessToken {

    private String accessToken;
    private long expiresTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public AccessToken(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        expiresTime=System.currentTimeMillis()+Integer.parseInt(expiresIn)*1000;
    }

    /**
    *@MethodName: isExpired
    *@Description: 判断token是否过期
    *@Param:
    *@Return: * @return: boolean
    *@Author: guanwanhang
    *@Date: 2020/7/26
    **/
    public boolean isExpired(){
        return System.currentTimeMillis()>expiresTime;
    }

}
