package com.example.datastructure.demo.tree1;

/**
 * 顺序存储二叉树
 */
public class ArrayBinaryTree {

    int[] data;

    public ArrayBinaryTree(int[] data){
        this.data=data;
    }

    public void frontShow(){
        frontShow(0);
    }


    //前序遍历
    public void frontShow(int start){
      if(data==null || data.length == 0){
          return;
      }
      //先遍历当前节点的内容
        System.out.println(data[start]);
        //遍历左节点 2*index+1
        if(2*start+1<data.length){
            frontShow(2*start+1);
        }
      //遍历右子节点
        if(2*start+2<data.length){
            frontShow(2*start+2);
        }
    }



}
