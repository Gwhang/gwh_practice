package com.example.datastructure.demo.tree;

/**
 * 二叉树
 */
public class BinaryTree {

    TreeNode root;
    //设置根节点
    public void setRoot(TreeNode root) {
        this.root = root;
    }
    // 获取根节点
    public TreeNode getRoot() {
        return root;
    }
    //前序遍历
    public void frontShow(){
        root.frontShow();
    }
    //中序遍历
    public void midShow(){
        root.midShow();
    }
    public void afterShow(){
        root.afterShow();
    }

    //前序查找
    public TreeNode frontSearch(int number){
        return root.frontSearch(number);
    }
    //删除子节点
    public void delete(int number){
        if (root.value==number){
            root = null;
        }
        root.delete(number);
    }

}
