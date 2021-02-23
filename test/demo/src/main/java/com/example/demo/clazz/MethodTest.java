package com.example.demo.clazz;

import java.lang.reflect.Method;

/**
 * Method 代表类的方法（不包括构造方法）
 */
public class MethodTest {

    public static void main(String[] args) throws Exception{
        Class<User> userClass=User.class;
        User user=new User("张三",23,"123456");
       // Method method = userClass.getDeclaredMethod("getName");
        Method method = userClass.getDeclaredMethod("getIdNumber");
        // 暴力反射 ，忽略访问权限修饰符
        method.setAccessible(true);
        // 传递obj对象及参数调用该对象对应的方法 执行指定对象的方法
        Object invoke = method.invoke(user);
        System.out.println(invoke);
        // set方法
        Method method2 = userClass.getDeclaredMethod("setIdNumber", String.class);
        method2.setAccessible(true);
        Object invoke1 = method2.invoke(user, "666");
        System.out.println(user);


    }

}
