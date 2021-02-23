package com.example.demo.clazz;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationTest {

    public static void main(String[] args) throws Exception{
        // 获取用户反射
        Class<User> userClass = User.class;
        // 获取注解
        MyAnnotation annotation = userClass.getAnnotation(MyAnnotation.class);
        if(annotation!=null){
            System.out.println(annotation.value());
        }
        System.out.println("--------------------------------");
        User user=new User();
        //获取所有字段
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field field:declaredFields){
            //获取字段注解
            MyAnnotation annotation1 = field.getAnnotation(MyAnnotation.class);
            if(annotation1!=null){
                // 获取注解值
                String value= annotation1.value();
                // 暴力反射
                field.setAccessible(true);
                // 设置字段值为注解值
                field.set(user,value);

            }

        }
        System.out.println(user);
    }

}
