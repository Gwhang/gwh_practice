package com.example.datastructure.demo;

public class DoubleNodeTest {

    public static void main(String[] args) {
        //创建节点
        DoubleNode n1 = new DoubleNode(1);
        DoubleNode n2 = new DoubleNode(2);
        DoubleNode n3 = new DoubleNode(3);
        //追加节点
        n1.after(n2);

        //查看上一个节点，自己，下一个节点的内容
        System.out.println(n1.pro.getData());
        System.out.println(n1.getData());
        System.out.println(n1.next.getData());


    }

}
