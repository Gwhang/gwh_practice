package com.example.demo.clazz;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证通过反射擦除泛型
 */
public class ListTest {

    public static void main(String[] args) throws Exception{
        List<User> userList = new ArrayList<User>();
        userList.add(new User("张三",24,"12324"));
        userList.add(new User("李四",24,"12334"));
        userList.add(new User("王五",24,"32324"));

        Class<? extends List> userListClass = userList.getClass();
        Method method = userListClass.getDeclaredMethod("add", Object.class);
        // 泛型会在运行阶段被擦除，这个时候集合一类的对象就可以强制往里面放入非指定泛型的数据
        method.invoke(userList,"hhh");
        method.invoke(userList,"123");
        System.out.println(userList);


    }

}
