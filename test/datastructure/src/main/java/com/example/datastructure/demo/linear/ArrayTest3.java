package com.example.datastructure.demo.linear;

import java.util.Arrays;

/**
 * 删除数组元素
 */
public class ArrayTest3 {

    public static void main(String[] args) {
        //目标数组
        int[] arr=new int[]{9,8,7,6,5,4,3};
        System.out.println(Arrays.toString(arr));
        //要删除的元素下标
        int dst = 4;
        //创建一个新的数组，长度是原数组长度 -1
        int[] newArr=new int[arr.length-1];
        //复制原数组中除了要删除的那个元素
        for (int i = 0 ;i< newArr.length ; i++){
            if (i<dst){
                newArr[i]=arr[i];
            }else{
                newArr[i]=arr[i+1];
            }
        }
        arr=newArr;
        System.out.println(Arrays.toString(arr));


    }


}
