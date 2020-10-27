package com.example.datastructure.demo.algorithm;

import java.util.Arrays;

/**
 * 希尔排序
 * 拿总长度除以2取整，计算出步长，第一组数开始按照步长例如步长为4，第一组数的比较对象下标从0开始到下标4，然后从4再开始向后延伸4位为
 * 第一组的比较对象，然后第二组从第二位开始，延长步长为4，第三组也延长步长为4。。。。直到每一组都有对象可比较，然后以第一组比较为例
 * 比较起来比较像插入排序，从组中的每一个数字进行比较替换和复制，直到按顺序排列，一下每组都是如此，然后再拿之前取到的步长4除以2得2
 * 再进行分组，下标从0开始到下标4，然后分成2组接着比较，比较完成后，接着步长2除以2得1，然后分组，这次分组就是步长为1，最终演变为
 * 11比较
 *
 *
 */
public class HillSort {

    public static void main(String[] args) {
        int[] arr={7,8,7,4,5,89,4,1,2,50,4,7,41,1,0};
        HillSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 希尔排序
     * @param arr
     */
    public static void HillSort(int[] arr){
        //遍历所有的步长 步长等于总长度除以2，d必须大于0，每次比较结束，步长除以2
        for (int d=arr.length/2;d>0;d/=2){
            //遍历所有元素
            for (int i=d;i<arr.length;i++){
                //遍历本组中的所有元素
                for (int j=i-d;j>=0;j-=d){
                    //如果当前元素大于加上步后的那个元素，就替换
                    if(arr[j]>arr[j+d]){
                        int temp=arr[j];
                        arr[j]=arr[j+d];
                        arr[j+d]=temp;
                    }
                }
            }

        }

    }

}
