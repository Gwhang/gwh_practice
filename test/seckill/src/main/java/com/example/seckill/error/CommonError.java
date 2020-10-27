package com.example.seckill.error;

/**
 * 统一错误管理
 */
public interface CommonError {

    public int getErrCode();

    public String getErrMsg();

    public CommonError setErrMsg(String errMsg);
}


