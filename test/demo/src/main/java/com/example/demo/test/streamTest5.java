package com.example.demo.test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream测试合集
 *
 * Stream可以由数组或集合创建，对流的操作分为两种：
 * 中间操作，每次返回一个新的流，可以有多个。
 * 终端操作，每个流只能进行一次终端操作，终端操作结束后流无法再次使用。终端操作会产生一个新的集合或值
 *
 * Stream有几个特性：
 * stream不存储数据，而是按照特定的规则对数据进行计算，一般会输出结果。
 * stream不会改变数据源，通常情况下会产生一个新的集合或一个值。
 * stream具有延迟执行特性，只有调用终端操作时，中间操作才会执行。
 *
 *在使用stream之前，先理解一个概念：Optional
 * Optional类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象
 *
 */
public class streamTest5 {

    public static void main(String[] args) {
        // 1.遍历/匹配（foreach/find/match）
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        List<String> listSplit = Arrays.asList("m,k,l,a", "1,3,5,7");

        // 遍历符合元素规则的数据
        list.stream().filter(x -> x>6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        if(findFirst.isPresent()){
            System.out.println("匹配第一个值为:"+findFirst.get());
        }
        // 匹配任意
        Optional<Integer> findAny = list.stream().filter(x -> x > 6).findAny();
        if (findAny.isPresent()){
            System.out.println("匹配任意一个值:"+findAny.get());
        }
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x > 6);
        System.out.println("是否存在大于6的值:"+anyMatch);
        System.out.println(" ======================================= ");
        // 2 筛选（filter）
        // 筛选出Integer集合中大于7的元素，并打印出来
       list.stream().filter(x -> x > 7).forEach(System.out::print);
        // 筛选员工中工资高于8000的人，并形成新的集合。 形成新集合依赖collect（收集
        List<Person> personList = getPersonList();
        List<String> filetList=personList.stream().filter(x -> x.getSalary()>8000)
                .map(Person::getName).collect(Collectors.toList());
        System.out.println(filetList);
        System.out.println(" ==================================================== ");
       // 3 聚合（max/min/count)
        List<String> strList = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
       // 获取String集合中最长的元素。
        Optional<String> max = strList.stream().max(Comparator.comparing(String::length));
        if (max.isPresent()){
            System.out.println("最长元素："+max.get());
        }
        // 获取Integer集合中的最大值
        Optional<Integer> max1 = list.stream().max(Integer::compareTo);
        if (max1.isPresent()){
            System.out.println("自然比较最大值为："+max1.get());
        }
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        if (max2.isPresent()){
            System.out.println("自定义比较最大值为："+max2.get());
        }
        // 获取员工工资最高的人
        Optional<Person> max3 = personList.stream().max(Comparator.comparing(Person::getSalary));
        if (max3.isPresent()){
            System.out.println("员工工资最高的人为："+max3.get());
        }
        // 计算Integer集合中大于6的元素的个数
        long count = list.stream().filter(x -> x > 6).count();
        System.out.println("元素大于6的个数为："+count);
        System.out.println("==================================================");
        // 4 映射(map/flatMap)
        // 映射，可以将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap：
        // map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        // flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        // 英文字符串数组的元素全部改为大写。整数数组每个元素+3。
        List<String> newStrList=strList.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("字母大写:"+newStrList);
        List<Integer> integerList= list.stream().map(x -> x+3).collect(Collectors.toList());
        System.out.println("每个值+3"+integerList);
        // 将员工的薪资全部增加1000。
        // 不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());

        // 改变原来员工集合的方式
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前：" + personList.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        // 将两个字符数组合并成一个新的字符数组。
        List<String> collect = listSplit.stream().flatMap(x -> {
            // 将每个元素转换成一个stream
            String[] split = x.split(",");
            Stream<String> newList = Arrays.stream(split);
            return newList;
        }).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("========================================");
        // 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
        // 求和
        Optional<Integer> sum1 = list.stream().reduce((x, y) -> x + y);
        if (sum1.isPresent()){
            System.out.println("求和1："+sum1.get());
        }
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        if (sum2.isPresent()){
            System.out.println("求和2:"+sum2.get());
        }
        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);
        if (product.isPresent()){
            System.out.println("乘积："+product.get());
        }
        // 最大值
        Optional<Integer> max4 = list.stream().reduce(Integer::max);
        if(max4.isPresent()){
            System.out.println("最大值:"+max4.get());
        }
        // 求所有员工的工资之和和最高工资
        Optional<Integer> salarySum = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        if (salarySum.isPresent()){
            System.out.println("所有员工工资之和:"+salarySum);
        }
        // 所有员工的最高工资
        Optional<Integer> salaryMax = personList.stream().map(Person::getSalary).reduce(Integer::max);
        if (salaryMax.isPresent()){
            System.out.println("员工最高工资"+salaryMax);
        }
        System.out.println("============================================");
        // collect，收集，可以说是内容最繁多、功能最丰富的部分了。从字面上去理解，就是把一个流收集起来，最终可以是收集成一个值也可以收集成一个新的集合。
        // collect主要依赖java.util.stream.Collectors类内置的静态方法。
        List<Integer> collect1 = list.stream().filter(x -> x > 6).collect(Collectors.toList());
        Set<Integer> collect2 = list.stream().filter(x -> x > 6).collect(Collectors.toSet());
        Map<String, Person> collect3 = personList.stream().filter(p -> p.getSalary() > 8000).collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("收集list:"+collect1+" 收集set:"+collect2+" 收集map:"+collect3.toString());
        System.out.println("===========================================");
        // Collectors提供了一系列用于数据统计的静态方法：
        // 求总数
        long count1 = personList.stream().count();
        System.out.println("总数:"+count1);
        // 求平均工资
        Double collect4 = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println("平均工资:"+collect4);
        // 求最高工资
        Optional<Integer> collect5 = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compareTo));
        System.out.println("最高工资:"+collect5.get());
        // 求工资之和
        Integer  collect6 = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        System.out.println("工资之和:"+collect6);
        DoubleSummaryStatistics collect7 = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("所有数据："+collect7);
        // 分组(partitioningBy/groupingBy)
        // 分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
        // 分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        System.out.println("薪资大于8000："+part);
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        System.out.println("性别分组:"+group);
        System.out.println("=================================================");
        // 接合(joining)
        // joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
        String collect8 = personList.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println("按照逗号拼接姓名:"+collect8);
        String collect9 = strList.stream().collect(Collectors.joining("-"));
        System.out.println("按照-拼接:"+collect9);
        System.out.println("===========================================");
        //  归约(reducing)
        // Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
        Integer collect10 = personList.stream().collect(Collectors.reducing(0, Person::getAge, (x, y) -> x + y));
        System.out.println("所有员工年龄之和:"+collect10);
        System.out.println("===========================================");
        // 提取/组合
        // 流也可以进行合并、去重、限制、跳过等操作。
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> collect11 = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        System.out.println("合并去重:"+collect11);
        // limit：限制从流中获得前n个数据
        List<Integer> collect12 = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        System.out.println("输出:"+collect12);
        // skip：跳过前n个数据
        List<Integer> collect13 = Stream.iterate(1, x -> x + 2).skip(3).limit(5).collect(Collectors.toList());
        System.out.println("跳过1个："+collect13);


    }

    /**
     * 获取对象集合
     * @return
     */
    public static List<Person> getPersonList(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
        return personList;
    }

}
class Person {
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区

    // 构造方法
    public Person(String name, int salary, int age,String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
