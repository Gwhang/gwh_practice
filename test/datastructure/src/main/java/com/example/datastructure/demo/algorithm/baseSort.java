package com.example.datastructure.demo.algorithm;

import java.util.Arrays;

/**
 * 基数排序
 * 我们先找1组无序的数字
 * 4  7  67 53 45 768 98 22 43 54 65 88
 * 然后定义10个数组
 * 0=int[]{},1=int[]{},2=int[]{},3=int[]{},4=int[]{},5=int[]{},6=int[]{},7=int[]{},8=int[]{}9=int[]{}
 * 开始第一次排序 按照个位数排序
 * 0=int[]{},1=int[]{},2=int[]{22},3=int[]{43,53},4=int[]{4,54},5=int[]{45,65},6=int[]{},7=int[]{7,67},8=int[]{768,98,88}9=int[]{}
 * 放进去以后开始从数组0开始取出数据 按照放入顺序取出
 * 数组为空的我们跳过 最终取到的数据如下 22,43,53,4,54,45,65,7,67,768,98,88
 * 开始第二次排序 按照十位排序
 * 0=int[]{4,7},1=int[]{},2=int[]{22},3=int[]{},4=int[]{43,45},5=int[]{53,54},6=int[]{65,67,768},7=int[]{},8=int[]{88}9=int[]{98}
 * 放进去以后开始从数组0开始取出数据 按照放入顺序取出
 * 最终得到的数据如下
 * 4,7,22,43,45,53,54,65,67,768,88,98
 * 开始第三次排序 按照百位排序
 * 0=int[]{4,7,22,43,45,53,54,65,67,88,98},1=int[]{},2=int[]{},3=int[]{},4=int[]{},5=int[]{},6=int[]{},7=int[]{768},8=int[]{}9=int[]{}
 * 放进去以后开始从数组0开始取出数据 按照放入顺序取出
 * 最终得到的数据如下
 * 4,7,22,43,45,53,54,65,67,88,98，768
 * 现在发现数据已经有序了
 * 我们只排序了3次，规则我们排序的次数是无序数据中最大数值的位数 这个就是基数排序
 *
 *
 */
public class baseSort {

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
        //用于临时存储数据的数组 一共需要10个数组  每个数组中最大放arr的长度
        int[][] temp=new int[10][arr.length];
        //用于记录在temp相应的数组中存放的数字的数量
        int[] counts= new int[10];

        //根据最大长度的数决定比较的次数
        for (int i = 0,n=1;i<maxLength;i++,n*=10){
            //把每个数字分别计算余数
            for (int j=0;j<arr.length;j++){
               //计算余数
                int ys=arr[j]/n%10;
                //把当前遍历的数据放入指定的数组中
                temp[ys][counts[ys]]=arr[j];
                //记录数量
                counts[ys]++;
            }
            //记录取到元素需要放的位置
            int index=0;
            //把数字取出来
            for (int k =0;k< counts.length;k++){
                //记录数量的数组中当前余数记录的数量不为0
                if (counts[k]!=0){
                    //循环取出元素
                    for (int l=0;l<counts[k];l++){
                        //取出元素
                        arr[index] = temp[k][l];
                        //取出元素以后 记录下一个位置
                        index++;
                    }
                    //把数量置为0
                    counts[k] = 0;
                }
            }
        }

    }


}
