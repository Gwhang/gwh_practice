package com.example.demo.pattern.chainOfResponsibility;

/**
 * SchoolMasterHandler：具体处理者校长
 */
public class SchoolMasterHandler extends Handler{
    public SchoolMasterHandler(String name) {
        super(name);
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        super.setNextHandler(nextHandler);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() > 10000) {
            // 金额大于 10000 由校长处理
            System.out.println("请求编号 id:" + purchaseRequest.getId() + "被" + this.name + "处理, 金额" + purchaseRequest.getPrice());
        } else {
            // 不在范围之内,交给下一个 handler 处理
            nextHandler.processRequest(purchaseRequest);
        }
    }
}
