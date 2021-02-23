package com.example.demo.clazz;

import java.lang.reflect.Constructor;

/**
 * Constructor: 代表类的构造方法
 */
public class ConstructorTest {

    public static void main(String[] args) throws Exception{
        Class<User> userClass = User.class;
        // 调用共有的构造方法 创建对象
        User user = userClass.newInstance();
        System.out.println(user);
        // Constructor 可以创建有参构造方法，并且无视方法访问权限
        Constructor<User> declaredConstructor = userClass.getDeclaredConstructor(String.class, Integer.class, String.class);
        User user1 = declaredConstructor.newInstance("张三", 23, "123456");
        System.out.println(user1);


    }

}
