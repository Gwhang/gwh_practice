package com.example.demo.pattern.decorator;

/**
 * 装饰器（Decorator）模式又名包装(Wrapper)模式，是一种能动态将新功能附加到对象上。
 * 在对象功能扩展方面，它比继承更有弹性，装饰器模式也体现了开闭原则（ocp），是一种继承关系的替代方案。
 *
 * 装饰器模式的四个角色
 * 抽象构件（Component）角色：给出一个抽象接口，已规范准备接受附加责任对象。
 * 具体构件（ConcreteComponent）角色：定义一个将要接受附加责任的类。
 * 装饰（Decorator）角色：持有一个构件（Component）对象的实例，并定义一个与抽象构件接口一致的接口。
 * 具体装饰（ConcreteComponent）角色：负责给构件对象“贴上”附加的责任。
 *
 */
public class Test {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        // 第一种写法
        Component concreteDecoratorA = new ConcreteDecoratorA(component);
        concreteDecoratorA.sampleOperation();
        Component concreteDecoratorB = new ConcreteDecoratorB(component);
        // 第二种写法
        //Component concreteDecoratorB = new concreteDecoratorB(new concreteDecoratorA(component));
        concreteDecoratorB.sampleOperation();
    }

}
