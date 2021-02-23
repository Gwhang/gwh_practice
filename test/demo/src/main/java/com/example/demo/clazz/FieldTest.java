package com.example.demo.clazz;

import java.lang.reflect.Field;

/**
 * Field 代表类的成员变量。成员变量和成员属性是两个概念。用get/set方法来区分，如果一个类里面有name变量，没有get/set 方法，则这个类只有
 * name这个字段，如果有get/set方法时，则这个类拥有name属性
 */
public class FieldTest {

    public static void main(String[] args) throws Exception{
        Class<User> userClass=User.class;
        User user=new User("张三",23,"123456");
        Field field = userClass.getDeclaredField("name");
        // 获取 obj中对应的公共方法的属性值
       // System.out.println(field.get(user));

        // 暴力反射 ，忽略访问权限修饰符
        field.setAccessible(true);
        System.out.println(field.get(user));

        // 设置obj中对应属性值
        field.set(user,"李四");
        System.out.println(field.get(user));

    }

}
