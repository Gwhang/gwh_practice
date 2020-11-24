package com.example.datastructure.demo.tree;

/**
 * 二叉树测试
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        //创建二叉树
        BinaryTree binaryTree=new BinaryTree();
        //创建一个根节点
        TreeNode root=new TreeNode(1);
        //把根节点赋值给树
        binaryTree.setRoot(root);
        //创建右节点
        TreeNode rootR=new TreeNode(3);
        //设置右节点
        root.setRightNode(rootR);
        //创建左节点
        TreeNode rootL=new TreeNode(2);
        //设置左节点
        root.setLeftNode(rootL);
        //为第二层的左节点创建2个子节点
        rootL.setLeftNode(new TreeNode(4));
        rootL.setRightNode(new TreeNode(5));
        //为第二层的右节点创建2个子节点
        rootR.setLeftNode(new TreeNode(6));
        rootR.setRightNode(new TreeNode(7));

        //遍历树
        // 前序遍历
        binaryTree.frontShow();
        System.out.println("=================");
        //中序遍历
        binaryTree.midShow();
        System.out.println("=================");
        //后序遍历
        binaryTree.afterShow();
        System.out.println("=================");
        //前序查找
        TreeNode result=binaryTree.frontSearch(5);
        System.out.println(result);
        //删除子树
        binaryTree.delete(5);
        binaryTree.frontShow();


    }

}
