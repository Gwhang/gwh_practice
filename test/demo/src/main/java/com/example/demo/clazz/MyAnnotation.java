package com.example.demo.clazz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    /**
     * 值
     * @return
     */
    String value();

    /**
     * 长度
     * @return
     */
    int num() default 0;

}
