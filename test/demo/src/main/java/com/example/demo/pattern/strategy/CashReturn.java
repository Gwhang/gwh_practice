package com.example.demo.pattern.strategy;

//这是满**返**的具体策略类，承父类，重写抽象方法。
//在实例化对象的时候，需要传入两个参数,
//moneyCondition:满多少钱
//moneyReturn:返多少钱
public class CashReturn extends CashSuper {

    private double moneyCondition=0;
    private  double moneyReturn=0;

    public CashReturn(String moneyCondition,String moneyReturn){
        this.moneyCondition=Double.valueOf(moneyCondition);
        this.moneyReturn=Double.valueOf(moneyReturn);
    }

    @Override
    public double acceptCash(double money) {
        return money-Math.floor(money/moneyCondition)*moneyReturn;
    }
}
