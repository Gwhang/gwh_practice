package com.example.demo.pattern.chainOfResponsibility;

/**
 * Handler：抽象处理
 * 内部定义了两个属性name名字和nextHandler下一个处理者。以及一个processRequest处理请求方法，由子类自己去实现。
 */
public abstract class Handler {

    Handler nextHandler;
    String name;

    public Handler(String name) {
        this.name = name;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 处理审批流程的方法,不同的子类根据自己的情况来实现
     * @param purchaseRequest 请求
     */
    public abstract void processRequest(PurchaseRequest purchaseRequest);
}
