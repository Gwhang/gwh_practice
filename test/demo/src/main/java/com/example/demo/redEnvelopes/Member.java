package com.example.demo.redEnvelopes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//定义普通群员类
public class Member extends User {

    public Member() {
    }

    public Member(String name, BigDecimal leftMoney) {
        super(name, leftMoney);
    }

    // 收红包   receive
    public void receive(List<BigDecimal> list) {
        // 构造一个随机类对象 Random
        Random r = new Random();
        // 随机一个数是list容器中的索引  索引的取值范围 [0,list.size()]
        int index = r.nextInt(list.size());
        // 当去取一个红包,那么list容器中就要少一个红包
        BigDecimal money = list.remove(index);
        // 普通成员的余额需要增加对应的值
        super.setLeftMoney(super.getLeftMoney().add(money));
        System.out.println(getName()+"拆到了:"+getLeftMoney());
    }
}
