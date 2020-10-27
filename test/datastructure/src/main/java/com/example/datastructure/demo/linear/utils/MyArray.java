package com.example.datastructure.demo.linear.utils;

import java.util.Arrays;

/**
 * 工具类
 */
public class MyArray {
    //用于存储数据的数组
    private int[] elements;


    public MyArray(){
        //初始化数组
        elements=new int[0];
    }

    //获取数组长度
    public int getSize(){
        return elements.length;
    }

    //往数组末尾添加一个元素
    public void add(int element){
        //创建一个新数组，数组的长度是原数组长度+1
        int[] newArray=new int[elements.length+1];
        //循环将原数组数据放入到新数组中
        for (int i = 0;i<elements.length;i++){
            newArray[i]=elements[i];
        }
        //将元素添加到新数组中
        newArray[elements.length]=element;
        //数组替换
        elements=newArray;
    }

    //打印数组到控制台
    public void show(){
        System.out.println(Arrays.toString(elements));
    }

    //删除指定下标元素
    public void delete(int index){
        //判断下标是否越界
        if(index<0 || index>elements.length-1){
            throw new RuntimeException("下标越界");
        }
        //创建一个新的数组，数组长度是原数组长度-1
        int[] newArray=new int[elements.length-1];
        //循环删除数组
        for (int i = 0;i< newArray.length;i++){
            //判断要删除的下标位置
            if(i<index){
                //想要删除的元素前面的元素
                newArray[i]= elements[i];
            }else{
                //想要删除元素后面的元素
                newArray[i]=elements[i+1];
            }
        }
        //数组替换
        elements=newArray;
    }
    //获取某个下标位置的元素
    public int getIndex(int index){
        if (index<0||index>elements.length-1){
            throw new RuntimeException("下标越界");
        }
        return elements[index];
    }
    //插入一个元素到指定位置
    public void insert(int index,int element){
        //创建一个新数组，数组长度是原数组长度+1
        int[] newArray=new int[elements.length+1];
        //循环遍历原数组
        for (int i = 0;i<elements.length;i++){
            //判断目标元素之前的位置
            if(i<index){
                newArray[i]=elements[i];
            }else{
                //判断目标元素之后的位置
                newArray[i+1]=elements[i];
            }
        }
        //将元素插入到新数组中
        newArray[index] = element;
        //新数组替换老数组
        elements=newArray;
    }

    //替换某个下标的元素
    public void set(int index,int element){
        if (index<0||index>elements.length-1){
            throw new RuntimeException("下标越界");
        }
        elements[index]=element;
    }

    //获取元素的下标 线性查找
    public int linearSeart(int element){
        //如果没有查到就返回-1
        int index=-1;
        //循环查找
        for (int i=0;i<elements.length-1;i++){
            if(elements[i] == element){
                index=i;
                break;
            }
        }
        return index;
    }

    //二分查找 二分查找要求元素必须是有序的
    public int binarySeart(int element){
        //记录开始位置
        int begin=0;
        //记录结束位置
        int end=elements.length-1;
        //记录中间位置
        int mid = (begin+end)/2;
        //记录目标的位置
        int index=-1;
        //循环查找目标位置
        while (true){
            //如果开始位置大于等于结束位置的时候 数组中没有这个元素 直接ruturn
            if(begin>=end){
                return index;
            }
            //判断是否等于中间位置
            if(elements[mid]==element){
                index = mid;
                break;
                //中间元素不是要查找的元素
            }else {
                //判断目标元素是不是小于中间元素
                if(elements[mid]>element){
                    //调整结束记录
                    end=mid-1;
                    //中间元素比目标元素小
                }else{
                    //调整开始元素
                    begin = mid+1;
                }
                //去出新的中间位置
                mid=(begin+end)/2;
            }
        }
        return index;
    }


}
