package com.example.datastructure.demo.tree;

/**
 * 节点
 */
public class TreeNode {

    //节点的权
    int value;
    //左节点
    TreeNode leftNode;
    //右节点
    TreeNode rightNode;

    public TreeNode(int value) {
        this.value = value;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
    //前序遍历
    public void frontShow(){
        //先遍历当前节点的内容
        System.out.print(value);
        //左节点
        if(leftNode!=null){
            leftNode.frontShow();
        }
        //右节点
        if(rightNode!=null){
            rightNode.frontShow();
        }

    }
    //中序遍历
    public void midShow(){
        //左节点
        if(leftNode!=null){
            leftNode.midShow();
        }
        //先遍历当前节点的内容
        System.out.print(value);
        //右节点
        if(rightNode!=null){
            rightNode.midShow();
        }
    }
    //后序遍历
    public void afterShow(){
        //左节点
        if(leftNode!=null){
            leftNode.afterShow();
        }
        //右节点
        if(rightNode!=null){
            rightNode.afterShow();
        }
        //先遍历当前节点的内容
        System.out.print(value);
    }
    //前序查找
    public TreeNode frontSearch(int number){
        TreeNode target=null;
        if(this.value==number){
            return this;
            //当前节点的值不是要查找的值
        }else {
            //查找左儿子 有可能查到有可能查不到
            if(leftNode != null){
                target = leftNode.frontSearch(number);
            }
            //如果不为空说明左儿子已经查找到
            if(target != null){
               return target;
            }
            //到右儿子查找
            if(rightNode != null){
                target = rightNode.frontSearch(number);
            }
        }
        return target;
    }
    //删除子树
    public void delete(int number){
        TreeNode parent = this;
        //判断左儿子
        if (parent.leftNode !=null && parent.leftNode.value==number){
            parent.leftNode = null;
            return;
        }
        //判断右儿子
        if (parent.rightNode != null && parent.rightNode.value==number){
            parent.rightNode = null;
            return;
        }
        // 递归检查并删除左儿子
        parent = leftNode;
        if(parent !=null ){
            parent.delete(number);
        }
        // 递归检查并删除右儿子
        parent = rightNode;
        if(parent !=null ){
            parent.delete(number);
        }

    }


}
