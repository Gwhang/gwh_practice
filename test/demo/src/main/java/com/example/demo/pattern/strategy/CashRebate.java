package com.example.demo.pattern.strategy;

//这是打折的具体策略类，在该类的实例对象进行初始化的时候，
//需要传入指定的打折的折扣参数moneyRebate，继承父类，重写抽象方法
public class CashRebate extends CashSuper {
    //定义默认折扣
    private double moneyRebate=1;
    //修改折扣调用此方法
    public CashRebate(String moneyRebate){
        this.moneyRebate=Double.valueOf(moneyRebate);
    }

    @Override
    public double acceptCash(double money) {
        return money*moneyRebate;
    }
}
