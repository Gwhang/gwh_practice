package com.example.demo.test;


import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream6 {

    public static void main(String[] args) {
       List<Staff> staffList=getStaffList();
       //合并年龄小于30员工数据
       String[] strArr={"1005"};
       List<String> strings= Arrays.asList(strArr);
       //获取所有员工编号
       List<String> strList=Lists.newArrayList();
       //获取年龄小于30岁的员工编号
       List<String> testList=Lists.newArrayList();
       //根据年龄分组
       Map<String, List<Staff>> collect = staffList.stream().collect(Collectors.groupingBy(Staff::getSex));
        List<List<Staff>> lists = collect.values().stream().collect(Collectors.toList());
        for (List<Staff> s:lists){
            strList.addAll(s.stream().map(Staff::getNumber).collect(Collectors.toList()));
            testList.addAll(s.stream().filter(p -> p.getAge() <30).map(Staff::getNumber).collect(Collectors.toList()));
        }

        System.out.println(strList);
        System.out.println(testList);
       //合并在同一个数组中
        System.out.println(Stream.concat(testList.stream(), strings.stream()).distinct().collect(Collectors.toList()));
       //创建一个更大的数组合并
        System.out.println(Stream.of(testList,strings).distinct().collect(Collectors.toList()));

        System.out.println(strList.stream().filter(p -> testList.contains(p)).collect(Collectors.toList()));

        strList.addAll(strings);
        System.out.println(strList);

    }

    public static List<Staff> getStaffList(){
        List<Staff> staffList= Lists.newArrayList();

        Staff staff1=new Staff("1001","宋江",30,"男","30000");
        Staff staff2=new Staff("1002","卢俊义",28,"男","10000");
        Staff staff3=new Staff("1003","孙二娘",25,"女","9000");
        Staff staff4=new Staff("1004","霍三娘",22,"女","7200");
        Staff staff5=new Staff("1005","鲁智深",30,"男","9990");
        Staff staff6=new Staff("1006","武松",27,"男","8888");
        staffList.add(staff1);
        staffList.add(staff2);
        staffList.add(staff3);
        staffList.add(staff4);
        staffList.add(staff5);
        staffList.add(staff6);
        return staffList;
    }


}
class Staff{

    //员工编号
    private String number;
    //员工姓名
    private String name;
    //员工年龄
    private Integer age;
    //员工性别
    private String sex;
    //员工工资
    private String salary;

    public Staff() {
    }

    public Staff(String number, String name, Integer age, String sex, String salary) {
        this.number = number;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
