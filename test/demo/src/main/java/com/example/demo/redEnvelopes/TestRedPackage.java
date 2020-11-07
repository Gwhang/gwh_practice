package com.example.demo.redEnvelopes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//测试类
public class TestRedPackage {

    public static void main(String[] args) {
        //构建一个群主
        //构建多个成员对象
        Lord lord = new Lord("小明", new BigDecimal(1000));
        Member m1 = new Member("小王", BigDecimal.ZERO);
        Member m2 = new Member("小刘", BigDecimal.ZERO);
        Member m3 = new Member("小孙", BigDecimal.ZERO);
        // 发红包了
       List<BigDecimal> list = lord.send(new BigDecimal("200"), 3);
        // 查看群主的余额
        lord.check();
        // 加一个判断 群主确定发出了红包
        if (list.size() == 0) {
            System.out.println("群主发红包失败了,再来");
        } else {
            // 普通成员收红包
            m1.receive(list);
            m1.check();
            m2.receive(list);
            m2.check();
            m3.receive(list);
            m3.check();
        }

    }

}
