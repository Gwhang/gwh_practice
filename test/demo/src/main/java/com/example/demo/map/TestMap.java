package com.example.demo.map;

/**
 * Map接口
 */
public interface TestMap<K,V> {

    /**
     * 获取集合的长度
     * @return
     */
    int size();

    /**
     * 判断集合是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 获取集合Value
     * @param key
     * @return
     */
    V get(K key);


    /**
     * 设置集合
     * @param key
     * @param value
     * @return
     */
    V put(K key, V value);


    interface Entry<K,V> {

        K getKey();

        V getValue();

    }


}
