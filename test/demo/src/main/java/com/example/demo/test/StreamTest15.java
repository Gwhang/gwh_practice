package com.example.demo.test;

import com.example.demo.pojo.User;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Lambda表达式  Function.identity() 将对象原样设置为key
 */
public class StreamTest15 {

    public static void main(String[] args) {
        List<User> list = userList();
        // Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式
        Map<String, User> map = list.stream().collect(Collectors.toMap(p -> p.getName(), Function.identity()));

        System.out.println(map);

    }

    public static List<User> userList(){
        List<User> userList = Lists.newArrayList();
        User user1 = new User();
        user1.setAge("18");
        user1.setName("张三");
        User user2 = new User();
        user2.setAge("20");
        user2.setName("李四");
        User user3 = new User();
        user3.setAge("22");
        user3.setName("王五");
        User user4 = new User();
        user4.setAge("28");
        user4.setName("小明");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return userList;
    }

}
