package com.example.datastructure.demo.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr={5,6,7,8,14,57,1,78,78,99};
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);

    }

    public static void bubbleSort(int[] arr){
        //控制循环多少轮
        for (int i = 0; i<arr.length-1;i++){
            //控制循环多少次
            for (int j=0;j<arr.length-1-i;j++){
                //判断前后大小，进行位置交换
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }


}
