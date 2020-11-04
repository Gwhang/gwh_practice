package com.example.demo.test;

import org.apache.commons.compress.utils.Lists;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据测试
 */
public class streamTest {

    public static void main(String[] args) {

        List<StudentInfo> list= Lists.newArrayList();

        StudentInfo info=new StudentInfo();
        info.setAge(20);
        info.setName("小米");
        info.setNumber(1457);
        StudentInfo info1=new StudentInfo();
        info1.setAge(25);
        info1.setName("张三");
        info1.setNumber(1458);
        list.add(info);
        list.add(info1);
        //遍历集合获取指定数据
        String s=list.stream().filter(i -> i.getAge().equals(25))
                .map(StudentInfo::getName).collect(Collectors.toList()).get(0);

        System.out.println(s);


    }


}

class StudentInfo {

    private Integer number;

    private String name;

    private Integer age;

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
}
