package com.example.demo.oop;

import com.alibaba.fastjson.JSONObject;

/**
 * 继承
 * 有的时候客观事物之间就存在一些关联关系，那么在表示成类和对象的时候也会存在一定的关联。
 * 例如 猫它是动物，就具有动物的基本属性，当我们定义猫这个类时，有些动物属性我们没必要重复定义，只需要让猫这个类继承动物这个类就可以了。
 *
 * 表现形式： A extends B
 * A： 子类 、 派生类
 * B： 父类 、 基类 、 超类
 *
 * 子类继承了父类的什么？
 * 除 构造方法 外的所有东西 ！！！
 *
 *  this 和 super 关键字的区别？（面试）
 * this 代表的是当前对象的引用  super 代表的是父类的引用
 *构造方法完成后，一个对象才被创建出来，但是我们又看到 this 出现在了构造方法里 ，
 * 此时对象还没有被创建出来， this 怎么能代表当前对象呢 ，所以 this 并不能代表当前对象！！！
 * 那么 this 其实代表的是当前对象的引用
 *
 * protected 关键字：
 * 我们发现,，如果把字段设为 private, 子类不能访问. 但是设成 public, 又违背了我们 “封装” 的初衷，
 * 所以我们引入了 protected 关键字
 * Java 中对于字段和方法共有四种访问权限：
 * （1）private: 类内部能访问, 类外部不能访问 （私有的）
 * （2）默认(也叫包访问权限 default): 类内部能访问, 同一个包中的类可以访问, 其他类不能访问
 * （3）protected: 类内部能访问, 子类和同一个包中的类可以访问, 其他类不能访问.（受保护的）
 * （4）public: 类内部和类的调用者都能访问（公共的）
 *
 * 重写 与 重载的区别 ？
 * 重载 ：（overload）
 * 1.参数的个数不同 2.参数的类型不同 3.参数的顺序不同
 * 重写 ： （override）
 * （1）方法名相同；
 * （2）参数列表相同（参数的个数，参数的类型）
 * （3）返回值也要相同
 *
 * final 的用法：
 * （1）final 可以修饰变量 （变为常量），只能初始化一次
 * final int SIZE = 10;
 * （2）final 可以修饰类 ，表示当前类不能被继承（final class Animal）
 * （3）final 可以修饰方法 ， 当前修饰的方法不能被重写
 *
 */
public class Test {

    public static void main(String[] args) {
        Cat cat=new Cat();
        cat.setSpeed("10米/秒");
        cat.setName("小喵");
        cat.setAge("2");
        cat.setWeight("500g");
        System.out.println(JSONObject.toJSONString(cat));

    }

}
