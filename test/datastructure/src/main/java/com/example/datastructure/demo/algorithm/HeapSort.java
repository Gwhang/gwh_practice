package com.example.datastructure.demo.algorithm;

import java.util.Arrays;

/**
 * 堆排序
 * 大顶堆 ：父节点永远都大于他的子节点
 * 小顶堆：父节点的值永远都小于他的子节点
 * 升序排列使用大顶堆
 * 降序排列使用小顶堆
 *
 *
 *
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr={9,6,8,7,0,1,10,4,2};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void heapSort(int[] arr){
        //开始位置是最后一个非叶子节点，即最后一个节点的父节点
        int start = (arr.length-1)/2;
        //结束位置，数组的长度-1
        for (int i=start;i>=0;i--){
            maxHeap(arr,arr.length,i);
        }
        //先交换数组中第0个和堆中最后一个数字交换位置，再把前面的处理为大顶堆
        for (int i=arr.length-1;i>0;i--){
            int temp = arr[0];
            arr[0]=arr[i];
            arr[i]=temp;
            maxHeap(arr,i,0);
        }

    }


    /**
     * 转换大顶堆
     * @param arr 数组
     * @param size 要调整的长度
     * @param index 当前节点
     */
    public static void maxHeap(int[] arr,int size,int index){
        //左子节点
        int leftNode=2*index+1;
        //右子节点
        int rightNode = 2*index+2;
        int max=index;
        //和两个子节点分别比对，找出最大的节点
        if(leftNode <size && arr[leftNode]>arr[max]){
            max=leftNode;
        }
        if(rightNode<size && arr[rightNode]>arr[max]){
            max=rightNode;
        }
        //交换位置 如果最大值不等于当前值
        if (max!=index){
            int temp=arr[index];
            arr[index]=arr[max];
            arr[max]=temp;
            //交换位置以后，可能会破坏直接排好的堆，所以，之前排号的堆需要重新调整
            maxHeap(arr,size,max);
        }


    }


}
