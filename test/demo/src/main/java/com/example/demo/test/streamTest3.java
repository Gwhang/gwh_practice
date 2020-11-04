package com.example.demo.test;

import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 集合处理
 */
public class streamTest3 {

    public static void main(String[] args) {
        List<String> list1 = Lists.newArrayList();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List<String> list2 = Lists.newArrayList();
        list2.add("3333");
        list2.add("4444");
        list2.add("5555");

        // 并集
        List<String> intersection=list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
        System.out.println("====== 并集 ======");
        intersection.stream().forEach(System.out::println);

        // 差集 (list1 - list2)
        List<String> reduce1 =list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
        System.out.println("===== 差集 ======");
        reduce1.stream().forEach(System.out::println);

        // 差集 (list1 - list2)
        List<String> reduce2=list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());
        System.out.println("===== 差集 =====");
        reduce2.stream().forEach(System.out::println);

        // 并集  一般有filter 操作时，不用并行流parallelStream ,如果用的话可能会导致线程安全问题
        List<String> listAll=list1.parallelStream().collect(Collectors.toList());
        List<String> listAll2=list2.parallelStream().collect(Collectors.toList());
        listAll.addAll(listAll2);
        System.out.println("===== 并集 =====");
        listAll.stream().forEach(System.out::println);

        // 去重并集
        List<String> listAllDistinct=listAll.stream().distinct().collect(Collectors.toList());
        System.out.println("===== 去重并集 =====");
        listAllDistinct.stream().forEach(System.out::println);


        List<Student> list= Lists.newArrayList();
        Student info=new Student();
        info.setAge(20);
        info.setName("小米");
        info.setNumber(1457);
        info.setClazz("14");
        Student info1=new Student();
        info1.setAge(20);
        info1.setName("张三");
        info1.setNumber(1458);
        info1.setClazz("14");
        Student info2=new Student();
        info2.setAge(20);
        info2.setName("小s");
        info2.setNumber(1459);
        info2.setClazz("14");
        Student info3=new Student();
        info3.setAge(20);
        info3.setName("迈克尔");
        info3.setNumber(1460);
        info3.setClazz("14");
        list.add(info);
        list.add(info1);
        list.add(info2);
        list.add(info3);

        List<Student> list3= Lists.newArrayList();
        Student s1=new Student();
        s1.setAge(20);
        s1.setName("小米");
        s1.setNumber(1457);
        s1.setClazz("14");
        Student s2=new Student();
        s2.setAge(20);
        s2.setName("张三");
        s2.setNumber(1458);
        s2.setClazz("14");
        Student s3=new Student();
        s3.setAge(20);
        s3.setName("詹姆斯");
        s3.setNumber(1501);
        s3.setClazz("15");
        list3.add(s1);
        list3.add(s2);
        list3.add(s3);
        //交集
        List<Student> intersectionObj = list.stream().filter(item -> list3.contains(item)).collect(Collectors.toList());
        System.out.println("============== 交集 =============");
        intersectionObj.stream().forEach(System.out::println);
        // 差集 (list - list2)
        List<Student> reduceObj1 =list.stream().filter(item -> !list3.contains(item)).collect(Collectors.toList());
        System.out.println("===== 差集 ======");
        reduceObj1.stream().forEach(System.out::println);




    }

}

class Student{
    private Integer number;

    private String name;

    private Integer age;

    private String clazz;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    //List里面装的是对象 一定要重写equals和hashcode方法。不然是比较的堆的内存地址
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return number.equals(student.number) &&
                name.equals(student.name) &&
                age.equals(student.age) &&
                clazz.equals(student.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, age, clazz);
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
