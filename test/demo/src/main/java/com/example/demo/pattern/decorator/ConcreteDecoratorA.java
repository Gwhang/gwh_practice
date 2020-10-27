package com.example.demo.pattern.decorator;

/**
 * 具体装饰器
 */
public class ConcreteDecoratorA extends Decorator{

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        super.sampleOperation();
        System.out.println("打开淘宝");
    }
}
