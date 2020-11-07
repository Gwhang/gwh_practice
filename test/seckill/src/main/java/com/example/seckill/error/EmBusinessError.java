package com.example.seckill.error;

/**
 * 定义枚举实现error接口
 */
public enum EmBusinessError implements CommonError{
    //通用错误类型20000
    PARAMETER_VALIDATION_ERROR(20001,"参数不合法"),
    UNKNOWN_ERROR(20002,"未知错误"),

    //10000开头为用户信息相关错误定义
    USER_NOT_EXIST(10001,"用户不存在"),
    //登录校验
    USER_LOGIN_EXIST(10002,"用户登录失败"),
    //30000开头为交易信息错误定义
    STOCK_NOT_ENOUGH(30001,"库存不足"),
    //20003 用户还未登录
    USER_NOT_LOGIN(20003,"用户还未登录"),
    ;

    private int errCode;
    private String errMsg;

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
