package com.example.datastructure.demo.algorithm;

import java.util.Arrays;

/**
 * 快速排序
 * 先定义1个基数和一个高位数，基数一般默认为第一位数，高位数默认为最后一位数，然后从后往前开始于基数比较，如果比基数大就放在右面，
 * 如果比基数小，就放左面，基数不动，高位数一直往前一一与之比较，如果高位走到比基数小的时候，把高位的数字复制一份替换基数，然后
 * 基数向前移动一位，然后比较次数与基数的大小，如果比基数小，不动，接着走高位，如果比基数大，就复制一份，替换高位，然后高位向前移动一位
 * 一直比较直到高位和基数重合，重合的位置将基数复制一份与之替换，基数作为分水岭，左边是小于他的，右边是大于他的，然后两边再按照上面的方式
 * 递归互相比较，直到开始位置和结束位置重合，就不需要再次
 *
 */
public class FastSort {

    public static void main(String[] args) {
        int[] arr={8,7,84,769,3,75,77,44,8,1,7,2,7,5,7,9};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 快速排序
     * @param arr 要排序的数组
     * @param start 开始位置
     * @param end 结束位置
     */
    public static void quickSort(int[] arr,int start,int end){
        //结束标识 如果高位和低位重合
        if(start<end){
            //首先获取基数，默认为0，但是因为使用的是递归，所以我们将使用start
            int stard = arr[start];
            //记录需要排序的下标
            int low=start;//低位数
            int high=end;//高位数
            //循环比较比标准数打的数和比标准数小的数
            while (low<high){
                //右边的数字比标准数字大
                while (low<high&&stard<=arr[high]){
                    //右边的下标向前移动一位
                    high--;
                }
                //使用右边的数字替换左边数字
                arr[low]=arr[high];
                //如果左边的数据比标准数数据小
                while (low<high&&stard>=arr[low]){
                    //左边下标向右移动一位
                    low++;
                }
                //使用左边的数字替换右边的数字
                arr[high]=arr[low];
            }
            //把标准数赋值给低位数所在位置的元素
            arr[low]=stard;
            //处理所有比标准数小的数组
            quickSort(arr,start,low);
            //处理所有比标准数大的数字
            quickSort(arr,low+1,end);

        }
    }

}
