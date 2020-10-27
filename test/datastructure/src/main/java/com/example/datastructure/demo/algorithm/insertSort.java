package com.example.datastructure.demo.algorithm;

import java.util.Arrays;

/**
 * 插入排序
 * 定义下标，从第二个数字开始，拿第二个数字与第一个数字做比较，如果第二个数字小于第一个数字那么就把第二个数字，先赋值给临时变量
 * 然后把第一个数字值替换第二个数字的值，然后再把临时变量中的值替换第一个数字的值，下标向后走，到第三个位置，如果第一个数字小于第
 * 二个数字那么下标直接向后移动一位，接着拿下标处数字与前一位做比较，如果比他大，那么下标直接向后走，如果比他小，那么按照之前操作
 * 替换前一位数，然后接着向前一位比较，直到没有比他更小的数字出现为止
 */
public class insertSort {

    public static void main(String[] args) {
        int[] arr={5,8,7,9,4,5,1,9,10};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertSort(int[] arr){
        //循环遍历数组比较 从第二位开始
        for (int i = 1; i<arr.length-1;i++){
            //如果当前数字比前一个数字小
            if(arr[i]<arr[i-1]){
                //创建临时变量，将当前数字存储起来
                int temp=arr[i];
                int j;
                //循环比较前面的数字
                for (j = i-1;j>=0&&temp<arr[j];j--){
                    //将前一位数替换为后一个数字
                    arr[j+1]=arr[j];
                }
                //如果都不满足条件的时候，把临时变量替换
                arr[j+1]=temp;
            }

        }

    }



}
