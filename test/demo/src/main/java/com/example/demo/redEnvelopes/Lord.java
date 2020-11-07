package com.example.demo.redEnvelopes;

import org.apache.commons.compress.utils.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * 定义群主类
 */
public class Lord extends User{

    /**
     * 每个红包总额最小金额
     */
    private static final BigDecimal  MIN = new BigDecimal("0.01");

    /**
     * 精度
     */
    private static final int  SCALE = 2;

    public Lord() {
    }

    public Lord(String name, BigDecimal leftMoney) {
        // super(参数列表) 永远要放到构造方法中的第一条语句中
        super(name, leftMoney);
    }


    /**
     * 发红包
     * 群主发红包,就是把一个整数的全额分成若干等份
     *      1.获取群主的余额,是否具备发红包的能力不能发红包则返回一个null,并且提示:"余额不足!"
     *      2.修改群主的余额,此时群主的余额 = 原来的余额 - 发的红包的额度
     *      3.拆分红包
     *           随机分配红包金额
     */
    public List<BigDecimal> send(BigDecimal money,int count){
        List<BigDecimal> list= Lists.newArrayList();
        // 数据的合法性检验
        // 获取群主的余额
        BigDecimal leftMoney=super.getLeftMoney();
        // 判断是否具备发红包的能力
        if (leftMoney.compareTo(money)<0) {
            System.out.println("余额不足");
            return list;
        }
        // 修改群主余额
        super.setLeftMoney(leftMoney.subtract(money));

        // 发红包
        list=SplitBigDecimalUtils.splitBigDecimalFromRange(money,count,SCALE,MIN,money.subtract(MIN));
        return list;
    }


}
