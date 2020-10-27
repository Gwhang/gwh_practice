package com.example.datastructure.demo.algorithm;

import java.util.Arrays;

/**
 * 选择排序
 * 第1次比较拿下标为0的开始向后比较，找到本组中最小的数字，然后将他与之交换位置，然后第2次比较，拿下标为1的开始比较
 * 找到本组中的最小数字，然后将之与下标为1的数字进行位置交换。。。
 *
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr={7,7,8,4,1,5,2,3,6,4,7,8,9,0,84};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        //循环遍历所有的数
        for (int i=0;i<arr.length;i++){
            int minIndex=i;
            //把当前遍历的数和后面所有的数依次进行比较，并记录下最小的数的下标
            for (int j=i+1;j<arr.length;j++){
                //如果后面比较的数笔记录的最小的数小
                if(arr[minIndex]>arr[j]){
                    //记录下最小那个数字的下标
                    minIndex=j;
                }
            }
            //如果最小的数和当前遍历数的下标不一致，说明下标为minIndex的数比当前遍历的数更小
            if(i!=minIndex){
                int temp=arr[i];
                arr[i]=arr[minIndex];
                arr[minIndex]=temp;
            }

        }

    }


}
