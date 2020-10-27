package com.example.demo.pattern.bridging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 抽象化角色：其定义了类的共有方法接口。抽象的角色是支付
 *
 * 支付类型桥接抽象类
 */
public abstract class Pay {

    Logger logger= LoggerFactory.getLogger(Pay.class);

    //桥接接口
    protected IPayMode payMode;

    //构造函数中用户方自行选择支付方式。
    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }
    //划账接口
    public abstract String transfer(String uId, String tradeId, BigDecimal amount);
}
