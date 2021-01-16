package com.example.demo.map;

import jdk.internal.org.objectweb.asm.tree.LdcInsnNode;

import java.util.LinkedHashMap;

/**
 * 测试
 */
public class Test02 {


    public static void main(String[] args) {

//        ArrayListHashMap<String,String> arrayListHashMap=new ArrayListHashMap<String,String>();
//        arrayListHashMap.put("test1","gwh");
//        arrayListHashMap.put("test1","gwh1");
//        System.out.println(arrayListHashMap.get("test1"));

        LinkeListHashMap<Object,String> linkedHashMap=new LinkeListHashMap<Object,String>();
        linkedHashMap.put("a","a");
        linkedHashMap.put(97,"97");

        System.out.println(linkedHashMap.get("a"));




    }

}
