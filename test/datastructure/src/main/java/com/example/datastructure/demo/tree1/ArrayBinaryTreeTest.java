package com.example.datastructure.demo.tree1;

/**
 * 顺序存储二叉树遍历
 */
public class ArrayBinaryTreeTest {

    public static void main(String[] args) {
        int[] data={1,2,3,4,5,6,7};
        //创建二叉树
        ArrayBinaryTree binaryTree=new ArrayBinaryTree(data);
        //前序遍历
        binaryTree.frontShow();
    }

}
