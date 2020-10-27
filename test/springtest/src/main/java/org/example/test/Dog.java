package org.example.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * 测试类
 */
public class Dog implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String name;
    private int age;

    public Dog() {
        System.out.println("这是一个无参构造方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 设置工厂名字
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("这里是BeanFactoryAware的setBeanFactory方法被调用");
    }

    /**
     * 设置bean名字
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("这里是BeanNameAware的setBeanName方法被调用");
    }

    /**
     * 销毁方法
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("这里是destory方法被调用");
    }

    /**
     * 初始化bean的时候执行，可以针对某个具体的bean进行配置。afterPropertiesSet 必须实现 InitializingBean接口。
     * 实现 InitializingBean接口必须实现afterPropertiesSet方法。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("这里是InitializingBean的afterPropertiesSet被调用");
    }

    /**
     * 初始化方法
     */
    public void initMethod(){
        System.out.println("这里是自定义init方法被调用");
    }

    /**
     * 销毁方法
     */
    public void destoryMethod(){
        System.out.println("这里是自定义的destory方法被调用");
    }

}
