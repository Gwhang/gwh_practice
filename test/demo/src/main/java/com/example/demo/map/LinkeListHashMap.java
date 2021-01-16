package com.example.demo.map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 基于LinkList实现HashMap
 */
public class LinkeListHashMap<K,V>  implements TestMap<K,V> {

    private  int size;

    private LinkedList<Node>[] objects=new LinkedList[100];

    /**
     * 相同的链表中存放相同的hashCode 但是对象值不相等
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return objects==null;
    }

    @Override
    public V get(K key) {
        //根据下标计算key 查询node
        int index=key.hashCode() % objects.length;
        //获取hashcode值相等的对象
        LinkedList<Node> nodes = objects[index];
        for (Node node:nodes){
            if(node.k.equals(key)){
                return node.v;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        //计算key存放的下标位置
        int index = key.hashCode() % objects.length;
        LinkedList<Node> nodes = objects[index];
        //如果没有产生hash碰撞
         if(nodes == null){
            //直接new 一个空的链表
             nodes = new LinkedList<Node>();
         }
         //如果产生冲突了
        nodes.add(new Node(key,value));
         objects[index] = nodes;

        return value;
    }

    class Node implements Entry<K,V>{

        private K k;
        private V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }


}
