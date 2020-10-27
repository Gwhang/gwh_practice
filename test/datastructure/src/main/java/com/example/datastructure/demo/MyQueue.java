package com.example.datastructure.demo;

/**
 * 队列 先进先出
 */
public class MyQueue {

    int[] elements;

    public MyQueue(){
        elements=new int[0];
    }

    //入队
    public void add(int element){
        //创建一个新的数组，数组长度是原数组长度+1
        int[] newArray=new int[elements.length+1];
        //将原数组元素复制到新数组元素中
        for (int i=0;i<elements.length;i++){
            newArray[i]=elements[i];
        }
        //将元素赋值到最后一个位置
        newArray[elements.length]=element;
        //替换元素
        elements=newArray;
    }
    //出队
    public int poll(){
        //获取第一个元素
        int element=elements[0];
        //创建一个数组，长度为原数组长度-1
        int[] newArray=new int[elements.length-1];
        //将原数组数据，存放到新数组中
        for (int i=0;i< newArray.length;i++){
            newArray[i]=elements[i+1];
        }
        //替换数组
        elements=newArray;
        return element;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return elements.length==0;
    }


}
