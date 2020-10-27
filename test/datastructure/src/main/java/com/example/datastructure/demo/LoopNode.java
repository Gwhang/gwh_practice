package com.example.datastructure.demo;

//循环链表
public class LoopNode {
    //节点内容
    int data;
    //下一个节点
    LoopNode next = this;

    //public Node(){}

    public LoopNode(int data){
        this.data=data;
    }

    //追加一个节点
    public void append1(LoopNode node){
        this.next=node;
    }

    //获取下一个节点
    public LoopNode next(){
        return this.next;
    }

    //获取节点中的数据
    public int getData(){
        return this.data;
    }


    //插入一个节点，作为当前节点的下一个节点
    public void after(LoopNode node){
        //取出下一个节点，作为下下个节点
        LoopNode nextNext=this.next;
        //把新节点设置为下一个节点
        this.next=node;
        //把下下个节点设置为新节点的下一个节点
        node.next=nextNext;

    }

    //删除下一个节点
    public void removeNode(){
        //获取下下个节点
        LoopNode newNode=this.next.next;
        //将下下个节点赋值到下个节点
        this.next=newNode;

    }


}
