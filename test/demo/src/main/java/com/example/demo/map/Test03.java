package com.example.demo.map;

import java.util.HashSet;

/**
 * hashSet
 *
 * hashset使用场景 去除重复项
 *
 */
public class Test03 {



    public static void main(String[] args) {
        //hashSet key 为什么不允许重复？
        //hashSet的底层是使用hashMap来包装的，他的add方法实际上就是往MAP集合中添加元素，hashSet值存放的就是hashmap的key的位置，对应生成
        //的hashmap的value则为空对象 ,因为hashmap的key是不允许重复的，所以hashset存放的值也是没有重复的
        // hashMap底层是用hashCode和equalse方法进行比较 来保证key唯一的

        HashSet<String> set=new HashSet<>();
        set.add("111");
        set.add("222");
        set.add("333");
        set.add("333");
        set.add("333");
        for (String str:set){
            System.out.println(str);
        }
        System.out.println(set.size());


    }



}
