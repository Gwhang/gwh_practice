package com.example.demo.pattern.strategy;
//简单工厂+策略结合，将实例化具体策略的过程由客户端转移到Context类中，是应用的简单工厂。
public class CashContext {

    CashSuper cs=null;

    public CashContext(String type){
        switch (type){
            case "1":cs=new CashNormal();break;
            case "2":cs=new CashRebate("0.8");break;
            case "3":cs=new CashReturn("300","100");break;
        }
    }

    public double GetResult(double money){
        return cs.acceptCash(money);
    }

}
