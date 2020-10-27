package com.example.demo.test;

/**
 * 整数比较
 */
public class IntegerComparison {

    /**
     * Integer 缓存是 Java 5 中引入的一个有助于节省内存、提高性能的特性。
     * Integer中有个静态内部类IntegerCache，里面有个cache[],也就是Integer常量池，常量池的大小为一个字节（-128~127）。
     * 这种 Integer 缓存策略仅在自动装箱的时候有用，使用构造器创建的 Integer 对象不能被缓存。
     */
    public static void main(String[] args) {
        Integer a=100;
        Integer b=100;
        Integer c=200;
        Integer d=200;
        System.out.println(a==b);//true
        System.out.println(c==d);//false

    }

}
