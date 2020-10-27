package org.example.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 后置处理器：初始化前后进行处理工作
 * 将后置处理器加入到容器中
 *
 * BeanPostProcessor，针对所有Spring上下文中所有的bean，可以在配置文档applicationContext.xml中配置一个BeanPostProcessor，
 * 然后对所有的bean进行一个初始化之前和之后的代理。BeanPostProcessor接口中有两个方法：
 * postProcessBeforeInitialization和postProcessAfterInitialization。 postProcessBeforeInitialization方法在bean初始化之前执行，
 * postProcessAfterInitialization方法在bean初始化之后执行。
 *
 */
public class TestBeanPostProcessor implements BeanPostProcessor {
    //bean初始化方法调用前被调用
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("这里是BeanPostProcessor的before方法被调用,BeanName:"+beanName);
        return bean;
    }

    //bean初始化方法调用后被调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("这里是BeanPostProcessor的after方法被调用,BeanName:"+beanName);
        return bean;
    }
}
