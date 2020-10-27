package com.example.demo.pattern.strategy;

import java.util.Scanner;

/**
 * 策略模式
 */
public class Program {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入收费类型:");
        String type=sc.next();
        System.out.print("请输入商品总价:");
        String money=sc.next();
        CashContext cscon=new CashContext(type);
        System.out.println("需要收费:"+cscon.GetResult(Double.valueOf(money)));
    }

}
