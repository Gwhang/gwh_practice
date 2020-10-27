package com.example.datastructure.demo.linear;

import java.util.Arrays;

/**
 * 数组的基本使用
 */
public class ArrayTest2 {

    public static void main(String[] args) {
        //解决数组长度不可变问题
        int[] arr = new int[] {9,8,7};
        //快速查看数组中的元素
        System.out.println(Arrays.toString(arr));
        //要加入的数组
        int dst = 6;
        //创建一个新的数组，长度是原数组的长度+1
        int[] newArr = new int[arr.length+1];
        //把原数组的数据全部复制到新数组中
        for (int i = 0; i<arr.length;i++){
            newArr[i]=arr[i];
        }
        newArr[arr.length]=dst;
        //替换原数组
        arr = newArr;
        System.out.println(Arrays.toString(arr));

    }

}
