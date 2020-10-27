package com.example.datastructure.demo;

/**
 * 递归
 * 在一个方法（函数）的内部调用该方法（函数）本身的编程方式就叫做递归
 */
public class Recursion {


    public static void main(String[] args) {
        //print(10);
        //System.out.println(febonacci(5));
        hanoi(2,'A','B','C');
    }

    //递归
    public static void print(int i){
        if(i>0){
            System.out.println(i);
            print(i-1);
        }
    }
    //斐波那契数列：1 1 2 3 5 8 13
    public static int febonacci(int i){
        if(i==1 || i==2){
            return 1;
        }else{
            return febonacci(i-1)+febonacci(i-2);
        }
    }


    /**
     * 汉诺塔问题
     * @param n 共有N个盘子
     * @param from 开始的柱子
     * @param in 中间的柱子
     * @param to 目标的柱子
     *  无论多少个盘子，都认为有2个盘子，上面的盘子和最下面的一个盘子
     */
    public static void hanoi(int n,char from,char in,char to){
        //只有一个盘子
        if(n==1){
            System.out.println("第1个盘子从"+from+"移动到"+to);
        }else {
            //移动上面所有的盘子移动到中间位置
            hanoi(n-1,from,to,in);
            //移动下面的盘子
            System.out.println("第"+n+"个盘子从"+from+"移动到"+to);
            hanoi(n-1,in,from,to);

        }

    }


}
