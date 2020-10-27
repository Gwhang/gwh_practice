package com.example.demo.pattern.strategy;

//这是正常收费的具体策略类，继承抽象类，完成方法的重写。
public class CashNormal extends CashSuper {

    @Override
    public double acceptCash(double money) {
        return money;
    }
}
