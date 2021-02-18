package com.example.demo.test;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * 1、为什么需要重写hashCode()方法和equals()方法？
 * 为什么要重写equals()方法。我们在定义类时，我们经常会希望两个不同对象的某些属性值相同时就认为他们相同，所以我们要重写equals()方法，按照原则，我们重写了equals()方法，也要重写hashCode()方法。
 * 来看一下Object.hashCode的通用约定（摘自《Effective Java》第45页）
 * 在一个应用程序执行期间，如果一个对象的equals方法做比较所用到的信息没有被修改的话，那么，对该对象调用hashCode方法多次，它必须始终如一地返回 同一个整数。在同一个应用程序的多次执行过程中，这个整数可以不同，即这个应用程序这次执行返回的整数与下一次执行返回的整数可以不一致。
 * 如果两个对象根据equals(Object)方法是相等的，那么调用这两个对象中任一个对象的hashCode方法必须产生同样的整数结果。
 * 如果两个对象根据equals(Object)方法是不相等的，那么调用这两个对象中任一个对象的hashCode方法，不要求必须产生不同的整数结果。然而，程序员应该意识到这样的事实，对于不相等的对象产生截然不同的整数结果，有可能提高散列表（hash table）的性能。
 * 如果只重写了equals方法而没有重写hashCode方法的话，则会违反约定的第二条：相等的对象必须具有相等的散列码（hashCode）。
 *
 * 2.在什么情况下需要重写hashCode()方法和equals()方法？
 *  当我们自定义的一个类，想要把它的实例保存在集合中时，我们就需要重写这两个方法；集合(Collection)有两个类，一个是List，一个是Set。
 *  List:集合中的元素是有序的，可以重复的；
 *  Set:无序，不可重复的；
 *
 *  HashSet存放元素时，根据元素的hashCode方法计算出该对象的哈希码，快速找到要存储的位置，然后进行比较，比较过程如下：
 *  如果该对象哈希码与集合已存在对象的哈希码不一致，则该对象没有与其他对象重复，添加到集合中！
 *  如果存在于该对象相同的哈希码，那么通过equals方法判断两个哈希码相同的对象是否为同一对象（判断的标准是：属性是否相同）
 *  相同对象，不添加。
 *  不同对象，添加。
 *  注意：如果返回值为false，则这个时候会以链表的形式在同一个位置上存放两个元素，这会使得HashSet的性能降低，因为不能快速定位
 *  还有一种情况就是两个对象的hashCode()返回值不同，但是equals()返回true，这个时候HashSet会把这两个对象都存进去，这就和Set集合不重复的规则相悖了
 *
 *
 */
public class hashcodeAndEqualsTest {

    public static void main(String[] args) {
      //  test1();
        Set<Clazz> set=new HashSet<>();

        Clazz clazz1=new Clazz("01","高一一班","西配楼5层左手边第一间");
        Clazz clazz2=new Clazz("01","高一一班","西配楼5层左手边第一间");
        Clazz clazz3=new Clazz("01","高一一班","西配楼5层左手边第一间");

        set.add(clazz1);
        set.add(clazz2);
        set.add(clazz3);

        System.out.println("集合长度为：" + set.size());

    }

    public static void test1(){
        Obj o1 = new Obj("张三","1001");
        Obj o2 = new Obj("张三","1001");
        System.out.println(o1.equals(o2)); //true
        System.out.println(o1.hashCode() == o2.hashCode());//true

    }

}

class Obj{

    private String name;
    private String number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obj obj = (Obj) o;
        return name.equals(obj.name) &&
                number.equals(obj.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    public Obj(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Obj() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

class Clazz{

    private String number;

    private String name;

    private String address;

    public Clazz() {
    }

    public Clazz(String number, String name, String address) {
        this.number = number;
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return number.equals(clazz.number) &&
                name.equals(clazz.name) &&
                address.equals(clazz.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, address);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}