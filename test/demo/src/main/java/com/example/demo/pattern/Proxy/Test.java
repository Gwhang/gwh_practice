package com.example.demo.pattern.Proxy;

public class Test {

    /**
     * 代理模式（Proxy），为其他对象提供一种代理,以控制对这个对象的访问。
     *优点
     * 职责清晰。真实的角色就是实现实际的业务逻辑，不用担心其他非本职责的事务。
     * 高扩展性。代理类完全可以在不做任何修改的情况下使用。
     */
    public static void main(String[] args) {
        ProxySubject proxySubject=new ProxySubject();
        //Subject是主题角色，定义了RealSubject和ProxySubject的共同接口；
        // RealSubject是具体主题角色，定义了ProxySubject所代表的真实实体；
        // ProxySubject为代理主题角色，保存一个引用使代理可以访问实体，并提供一个与Subject的接口相同的接口。
        proxySubject.requests();
    }


}
