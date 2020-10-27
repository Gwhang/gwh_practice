package com.example.datastructure.demo.linear;

/**
 * 线性结构
 * 数组
 * 特点：顺序存储
 */
public class ArrayTest1 {

    public static void main(String[] args) {
        //创建一个数组
        int[] arr1=new int[10];
        //获取数组长度
        System.out.println("length:"+arr1.length);
        //访问数组中的元素 数组名[下标] 注意：下标从0开始 最大可以取到长度-1
        int element1=arr1[0];
        System.out.println("element0："+element1);
        //为数组中的元素赋值
        arr1[0]=100;
        System.out.println("element0："+arr1[0]);
        arr1[1]=101;
        arr1[2]=102;
        arr1[3]=103;
        //遍历数组
        for (int i=0;i< arr1.length;i++){
            System.out.println(arr1[i]);
        }
        //创建数组的同时为数组中的元素赋值
        int[] arr2=new int[] {90,100,50,30,12};
        //获取数组的长度
        System.out.println("arr2.length:"+arr2.length);



    }



}
