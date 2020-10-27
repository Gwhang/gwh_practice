package com.example.datastructure.demo;

//一个节点
public class Node {
    //节点内容
    int data;
    //下一个节点
    Node next;

    //public Node(){}

    public Node(int data){
        this.data=data;
    }

    //追加一个节点
    public void append1(Node node){
        this.next=node;
    }

    //获取下一个节点
    public Node next(){
        return this.next;
    }

    //获取节点中的数据
    public int getData(){
        return this.data;
    }
    //追加一个节点
    public Node append(Node node){
        //当前节点
        Node currentNode=this;
        //循环向后找
        while (true){
            //取出下一个节点
            Node nextNode=currentNode.next;
            //如果下一个节点为null,当前节点已经是最后一个节点了
            if(nextNode==null){
                break;
            }
            //赋值给当前节点
            currentNode=nextNode;
        }
        //把需要追加的节点，追加为找到当前节点的下一个节点
        currentNode.next = node;
        return this;
    }
    //判断当前节点是否是最后一个节点
    public boolean isLast(){
        return this.next==null;
    }

    //显示所有节点
    public void show(){
        Node currentNode=this;
        while (true){
            System.out.print(currentNode.getData()+" ");
           //取出下一个节点，
            currentNode=currentNode.next;
            //如果是最后一个节点
            if (currentNode==null){
                break;
            }
        }
    }
    //插入一个节点，作为当前节点的下一个节点
    public void after(Node node){
        //取出下一个节点，作为下下个节点
        Node nextNext=this.next;
        //把新节点设置为下一个节点
        this.next=node;
        //把下下个节点设置为新节点的下一个节点
        node.next=nextNext;

    }

    //删除下一个节点
    public void removeNode(){
        //获取下下个节点
        Node newNode=this.next.next;
        //将下下个节点赋值到下个节点
        this.next=newNode;

    }


}
