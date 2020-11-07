package com.example.seckill.response;

/**
 * 返回通用对象
 */
public class CommonReturnType {

    //如果成功 返回success 失败返回 fail
    private String status;
    //若status等于 success 则data内返回前端需要的json数据
    //若status等于 fail 则data内使用通用的错误码格式
    private Object data;

    //定义通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result,String status){
        CommonReturnType type=new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
