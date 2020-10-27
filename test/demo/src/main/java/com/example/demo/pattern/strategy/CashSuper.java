package com.example.demo.pattern.strategy;

//先定义一个现金收费策略的抽象类，当然如果使用接口也可以
public abstract class CashSuper {
    public abstract double acceptCash(double money);
}
