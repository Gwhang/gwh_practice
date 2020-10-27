package com.example.demo.pattern.decorator;

/**
 * 具体构建
 */
public class ConcreteComponent implements Component{

    @Override
    public void sampleOperation() {
        System.out.println("打开浏览器");
    }
}
