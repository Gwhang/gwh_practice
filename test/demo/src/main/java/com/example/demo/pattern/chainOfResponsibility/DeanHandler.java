package com.example.demo.pattern.chainOfResponsibility;

/**
 * DeanHandler：具体处理者院长
 */
public class DeanHandler extends Handler{

    public DeanHandler(String name) {
        super(name);
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        super.setNextHandler(nextHandler);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() > 1000 && purchaseRequest.getPrice() <= 10000) {
            // 金额在 1000~10000 内,由院长处理
            System.out.println("请求编号 id:" + purchaseRequest.getId() + "被" + this.name + "处理, 金额" + purchaseRequest.getPrice());
        } else {
            // 不在范围之内, 交给下一个处理
            nextHandler.processRequest(purchaseRequest);
        }
    }
}
