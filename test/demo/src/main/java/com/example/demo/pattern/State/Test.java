package com.example.demo.pattern.State;

/**
 * 状态模式（State Pattern）属于行为模式。
 * 它主要用来解决对象在多个状态转换时，需要对外输出不同行为的问题。
 * 状态和行为是一一对应的，状态之间可以相互转换。当一个对象的内在状态发生改变时，允许改变其行为，这个对象看起来像是改变了类。
 *
 * 状态模式的三个角色
 * State（抽象状态角色）：定义一个接口，封装与Context的一个特定状态相关的行为。
 * ConcreteState（具体状态角色）：每一个子类实现一个与Context的一个特定状态相关的行为。
 * Context（环境角色）：用于维护State实例，这个实例定义当前的状态。
 *
 *
 *优点
 * 代码有很强的可读性。状态模式将每个状态的行为封装到对应的一个类中
 * 方便维护。将容易产生问题的 if-else 语句删除了，如果把每个状态的行为都放到一个类中，每次调用方法时都 要判断当前是什么状态，不但会产出很多 if-else 语句，而且容易出错
 * 符合“开闭原则”。容易增删状态
 *
 * 缺点
 * 会产生很多类。每个状态都要一个对应的类，当状态过多时会产生很多类，加大维护难度
 *
 * 应用场景
 * 当一个事件或者对象有很多种状态，状态之间会相互转换，对不同的状态要求有不同的行为的时候， 可以考虑使用状态模式
 *
 */
public class Test {

    public static void main(String[] args) {
        LightOff lightOff = new LightOff();
        LightOn lightOn = new LightOn();
        Context context = new Context();
        // 开灯
        lightOn.handle(context);
        System.out.println(context.getState());
        // 关灯
        lightOff.handle(context);
        System.out.println(context.getState());
    }

}
