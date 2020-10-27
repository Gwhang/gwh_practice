package com.example.datastructure.demo;

//测试类
public class NodeTest {

    public static void main(String[] args) {
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);
        //追加节点
//        n1.append1(n2);
//        n2.append1(n3);
        n1.append(n2).append(n3);
        //取出下一个节点
        System.out.println(n1.next().next().getData());
        //判断节点是否为最后一个节点
        System.out.println(n1.next().next().isLast());
        System.out.println("===================================");
        n1.show();
        //n1.removeNode();
        //n1.show();
        System.out.println("==================================");
        Node node=new Node(5);
        n1.next().after(node);
        n1.show();


    }

}
