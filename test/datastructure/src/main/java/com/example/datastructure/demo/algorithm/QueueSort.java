package com.example.datastructure.demo.algorithm;

import com.example.datastructure.demo.MyQueue;

import java.util.Arrays;

/**
 * 队列实现基数排序
 * 先进先出
 */
public class QueueSort {

    public static void main(String[] args) {
        int[] arr={23,45,34,231,435,76,54,776,44,34,2,5,3,7,9,10};
        baseSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 基数排序
     * @param arr
     */
    public static void baseSort(int[] arr){
        //存数组中最大的数组
        int max =Integer.MIN_VALUE;
        for (int i = 0;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        //求最大数字是几位数
        int maxLength = (max + "").length();
        //用于临时存储数据的队列的数组
        MyQueue[] temp=new MyQueue[10];
         //为队列数组赋值
        for (int i= 0;i< temp.length;i++){
            temp[i]=new MyQueue();

        }
        //根据最大长度的数决定比较的次数
        for (int i = 0,n=1;i<maxLength;i++,n*=10){
            //把每个数字分别计算余数
            for (int j=0;j<arr.length;j++){
                //计算余数
                int ys=arr[j]/n%10;
                //把当前遍历的数据放入指定的队列中
                temp[ys].add(arr[j]);
            }
            //记录取到元素需要放的位置
            int index=0;
            //把所有队列中的数字取出来
            for (int k =0;k< temp.length;k++){
                    //循环取出元素
                    while (!temp[k].isEmpty()){
                        //取出元素
                        arr[index] = temp[k].poll();
                        //取出元素以后 记录下一个位置
                        index++;
                     }
                    }
                }
    }

}
