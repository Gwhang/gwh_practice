package org.example.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    /**
     * Bean 的生命周期概括起来就是 4 个阶段：
     * 实例化（Instantiation）
     * 属性赋值（Populate）
     * 初始化（Initialization）
     * 销毁（Destruction）
     *
     * 1.首先通过spring上下文读取配置文件
     * 2.对我们的类Dog进行了实例化
     * 3.对类进行了属性赋值(跟踪源码可以看到)
     * 4.调用了BeanNameWare的SetBeanName方法
     * 5.调用了BeanFactoryAware的setBeanFactory方法
     * 6.调用了BeanPostProcessor的beforeInit（简写）方法
     * 7.调用了InitializingBean的afterPropertiesSet方法
     * 8.调用了init-method指向的自定义方法initMethod
     * 9.调用了BeanPostProcessor的afterInit（简写）方法
     * 10.Bean完成初始化处于可使用阶段
     * 11.调用了ClassPathXmlApplicationContext的destory方法
     * 12.调用了自定义的destory-method方法
     */
    public static void main(String[] args) {
        //加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //上下文销毁
        ((ClassPathXmlApplicationContext)context).destroy();
    }

}
