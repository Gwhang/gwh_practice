package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取集合中符合条件的数据
 */
public class Stream8 {

    public static void main(String[] args) {
        List<Animal> animalList = getAnimalTest();
        List<String> list = Lists.newArrayList();
        List<Animal> animals=Lists.newArrayList();
        list.add("1001");
        list.add("1002");
        list.add("1003");
        for (String str:list){
            animals.addAll(animalList.stream().filter(p ->p.getNumber().equals(str)).collect(Collectors.toList()));
        }
        System.out.println(JSONObject.toJSONString(animals));

    }

    public static List<Animal> getAnimalTest(){
        List<Animal> animals= Lists.newArrayList();
        Animal animal1=new Animal("1001","小猫",1,"捉老鼠");
        Animal animal2=new Animal("1002","小狗",2,"看家");
        Animal animal3=new Animal("1003","小猪",2,"睡觉");
        Animal animal4=new Animal("1004","鱼",2,"游泳");
        Animal animal5=new Animal("1005","松鼠",1,"上树");
        Animal animal6=new Animal("1006","小鸟",1,"偷东西");
        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal2);
        animals.add(animal3);
        animals.add(animal4);
        animals.add(animal5);
        animals.add(animal6);
        return animals;
    }


}



class Animal{

    //编号
    private String number;
    //名称
    private String name;
    //年龄
    private Integer age;
    //本领
    private String ability;

    public Animal() {
    }

    public Animal(String number, String name, Integer age, String ability) {
        this.number = number;
        this.name = name;
        this.age = age;
        this.ability = ability;
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

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}
