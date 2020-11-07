package com.example.demo.redEnvelopes;

import java.math.BigDecimal;

/**
 * 用户主类
 */
public class User {
    // 定义两个私有属性
    private String name;
    private BigDecimal leftMoney;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(BigDecimal leftMoney) {
        this.leftMoney = leftMoney;
    }

    public User() {
    }

    public User(String name, BigDecimal leftMoney) {
        this.name = name;
        this.leftMoney = leftMoney;
    }

    // 定义一个查看余额的方法
    public void check() {
        System.out.println("名字:" + name + "，余额为" + leftMoney + "元");
    }

}
