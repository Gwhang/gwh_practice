package com.example.datastructure.demo;

/**
 * 栈
 * 先进后出
 * 比如往箱子里面放书，一本一本的放进去，如果想拿出最开始放的那本，就必须先把后面放入的书，一本一本的拿出来，才能拿到最后一本书
 *
 */
public class MyStack {

    int[] elements;

    public MyStack(){
        elements=new int[0];
    }

    //压入栈
    public void push(int element){
        //创建一个新的数组
        int[] newArray=new int[elements.length+1];
        //把原数组中的元素复制到新数组中
        for (int i = 0;i<elements.length;i++){
            newArray[i]=elements[i];
        }
        //将元素添加至末尾
        newArray[elements.length]=element;
        //替换数组
        elements=newArray;
    }
    //取出栈顶元素 有2个步骤，1.获取末尾元素，2.更新原数组数据
    public int pop(){
        if (elements.length==0){
            throw new RuntimeException("栈中没有元素");
        }
        //取出栈顶元素
        int element=elements[elements.length-1];
        //创建一个新数组，数组长度为原数组长度-1
        int[] newArray=new int[elements.length-1];
        //将原数组元素都放在新数组中
        for (int i = 0;i<elements.length-1;i++){
            newArray[i]=elements[i];
        }
        //替换数组
        elements=newArray;
        return element;
    }
    //查看栈顶元素
    public int peek(){
        if (elements.length==0){
            throw new RuntimeException("栈中没有元素");
        }
        return elements[elements.length-1];
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return elements.length==0;
    }

}
