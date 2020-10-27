package com.example.demo.pattern.chainOfResponsibility;

/**
 * PurchaseRequest：请求消息
 * 请求消息用于各职责链之间的信息传递，里面可包含相关属性。
 *
 */
public class PurchaseRequest {

    /**
     * 请求类型
     */
    private int type;
    /**
     * 报销价格
     */
    private float price;

    private int id;

    public PurchaseRequest(int type, float price, int id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

}
