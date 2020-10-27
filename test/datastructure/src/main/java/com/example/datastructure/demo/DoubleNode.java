package com.example.datastructure.demo;

/**
 * 循环双向链表
 */
public class DoubleNode {

    //上一个节点
    DoubleNode pro = this;
    //下一个节点
    DoubleNode next=this;
    //节点数据
    int data;

    public DoubleNode(int data){
        this.data = data;
    }

    //增加节点
    public void after(DoubleNode node){
        //获取下一个节点
        DoubleNode nextNext=this.next;
        //把新节点作为当前节点的下一个节点
        this.next=node;
        //把当前节点做新节点的前一个节点
        node.pro=this;
        //让原来的下一个节点做新节点的下一个节点
        node.next=nextNext;
        //让原来的下一个节点的前一个节点做为新节点
        nextNext.pro=node;
    }

    //下一个节点
    public DoubleNode next(){
        return this.next;
    }

    //上一个节点
    public DoubleNode pro(){
        return this.pro;
    }
    //获取节点数据
    public int getData(){
        return this.data;
    }


}
