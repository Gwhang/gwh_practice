package com.example.demo.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap 与 HashTable的区别
 * HashMap 线程不安全的 执行效率高
 * HashTable 线程安全的 执行效率低
 * 当多个线程同时共享同一个变量做写的操作的时候，可能发生线程安全问题
 * 真的遇到线程安全问题 使用什么map集合
 *  ConcurrentHashMap  采用分段锁的原理 CAS无锁
 *  https://mp.weixin.qq.com/s/1yWSfdz0j-PprGkDgOomhQ 程序员小灰
 *
 *  HashMap可以存放空的key ,HashTable 不能存放空的key
 *  key为null的时候是不会有hashCode值的
 *
 *  HashMap 如果key是空的，同时没有hashCode那么 值存放在什么位置呢 默认存放table[0]
 *
 *
 * HashMap 是否可以存放key 为对象 可以
 *
 *
 * 扩展 hashcode 与equal()有什么区别
 * hashCode()方法和equal()方法的作用其实一样，在Java里都是用来对比两个对象是否相等一致，那么equal()既然已经能实现对比的功能了，为什么还要hashCode()呢？
 * 因为重写的equal（）里一般比较的比较全面比较复杂，这样效率就比较低，而利用hashCode()进行对比，则只要生成一个hash值进行比较就可以了，效率很高，那么hashCode()既然效率这么高为什么还要equal()呢？
 * 因为hashCode()并不是完全可靠，有时候不同的对象他们生成的hashcode也会一样（生成hash值得公式可能存在的问题，类似于hashMap的hash碰撞数组转链表），所以hashCode()只能说是大部分时候可靠，并不是绝对可靠，所以我们可以得出：
 *  1.equal()相等的两个对象他们的hashCode()肯定相等，也就是用equal()对比是绝对可靠的。
 *  2.hashCode()相等的两个对象他们的equal()不一定相等，也就是hashCode()不是绝对可靠的。
 *  所有对于需要大量并且快速的对比的话如果都用equal()去做显然效率太低，所以解决方式是，每当需要对比的时候，首先用hashCode()去对比，如果hashCode()不一样，则表示这两个对象肯定不相等（也就是不必再用equal()去再对比了）,如果hashCode()相同，
 *  此时再对比他们的equal()，如果equal()也相同，则表示这两个对象是真的相同了，这样既能大大提高了效率也保证了对比的绝对正确性！
 *
 * hashMap7存在的问题？ 数组+链表 扩容存在死循环的问题
 * 链表最大的缺陷是时间复杂度是 O(n)
 * 如果index冲突过多会导致链表的长度非常长，查询index 没有冲突 时间复杂度为O(1)
 * hash8升级 如果链表长度大于8的情况下 采用红黑树
 * 其次最大问题？ 头插法
 *
 *
 *
 */
public class Test01 {


    public static void main(String[] args) {

        HashMap<Object,String> hashMap=new HashMap<Object,String>();
        Test01 test01 = new Test01();
        hashMap.put(test01,"hashMap");
        System.out.println(hashMap.get(test01));
        Hashtable<String,String> hashtable=new Hashtable<String,String>();
        //hashtable.put(null,"hashTable");

        ConcurrentHashMap<String,String> concurrentHashMap=new ConcurrentHashMap<String,String>();
        //concurrentHashMap.put(null,"hashMap");


    }

}
