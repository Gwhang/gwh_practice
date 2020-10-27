package com.example.demo.pattern.chainOfResponsibility;

/**
 * DepartmentHandler：具体的处理者系主任
 * 继承Handler实现processRequest方法。
 */
public class DepartmentHandler extends Handler{

    public DepartmentHandler(String name) {
        super(name);
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        super.setNextHandler(nextHandler);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() <= 1000) {
            // 金额小于 1000, 由系主任自己处理
            System.out.println("请求编号 id:" + purchaseRequest.getId() + "被" + this.name + "处理, 金额" + purchaseRequest.getPrice());
        } else {
            // 金额大于 1000, 进入下一个 handler 处理
            nextHandler.processRequest(purchaseRequest);
        }
    }
}
