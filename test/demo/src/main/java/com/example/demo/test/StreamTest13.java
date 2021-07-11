package com.example.demo.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * JDK8中有双冒号的用法，就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下
 */
public class StreamTest13 {

    public static void printValue(String str){
        System.out.println("元素值为:"+str);
    }

    public static void main(String[] args) {
        List<String> al = Arrays.asList("a","b","c","d");

        // 将方法作为参数传入
        Consumer<String> consumer = StreamTest13::printValue;

        al.stream().forEach(p ->{
            //  JDK8中增加的接口Consumer内的accept方法，执行传入的方法参数
            consumer.accept(p);
        });



    }


}
