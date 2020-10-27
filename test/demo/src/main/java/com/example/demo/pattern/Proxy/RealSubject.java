package com.example.demo.pattern.Proxy;

public class RealSubject extends Subject{

    @Override
    public void requests() {
        System.out.println("this is real requests");
    }
}
