package com.example.demo.map;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于ArrayList实现HashMap
 */
public class ArrayListHashMap<K,V>  implements TestMap<K,V> {

    private int size;
    //存放多个键值对
    private List<Node> nodes= new ArrayList<Node>();

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return nodes==null;
    }


    @Override
    public V get(K key) {
       //根据key查询
        return getNode(key).getValue();
    }

    /**
     * 根据key查询
     * @param key
     * @return
     */
    private  Node getNode(K key){
        for (Node node:nodes){
            //时间复杂度 O(n) 从头查到尾
            if(key.equals(node.getKey())){
                return node;
            }

        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        //如果存在就修改
        Node node = getNode(key);
        if(node != null){
            node.v=value;
            return node.getValue();
        }
        //如果不存在就
       Node newNode = new Node(key,value);
        nodes.add(newNode);
        size++;
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
