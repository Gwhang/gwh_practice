package com.example.demo.pattern.bridging;

/**
 * 桥接核心
 *
 * 定义一个关于支付的接口，其就是实现化角色。这里的实现化可以理解为其是对某个维度变化的实现。
 * 这样抽象化角色就可以通过持有一个支付接口的实例以拥有具备该维度变化的能力，
 * 这也是该模式名称桥接的由来，即通过组合桥接的方式让付款方式与支付方式这两个独立的继承实现体系之间发生联系
 */
public interface IPayMode {

    //⽀付模式接⼝
    public boolean security(String uId);
}
