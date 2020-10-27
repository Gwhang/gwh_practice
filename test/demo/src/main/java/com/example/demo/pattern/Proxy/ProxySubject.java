package com.example.demo.pattern.Proxy;

/**
 * 代理类。一个代理类可以代理多个被委托者或被代理者，因此一个代理类具体代理哪个真实主题角色，是由场景类决定的。
 */
public class ProxySubject extends Subject{

    private RealSubject realSubject = null;

    public ProxySubject() {
        //初始化代理对象
        this.realSubject = new RealSubject();
    }

    @Override
    public void requests() {
        this.before();
        this.realSubject.requests();
        this.after();
    }

    private void before() {
        System.out.println("before______");
    }

    private void after() {
        System.out.println("after_____");
    }

}
