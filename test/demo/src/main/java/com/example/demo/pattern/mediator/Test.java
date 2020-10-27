package com.example.demo.pattern.mediator;

/**
 * 中介者模式（Mediator Pattern）属于行为型模式，它用一个中介对象来封装一系列的对象交互。
 * 中介者使各个对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
 *
 * 比如 MVC 模式，C（Controller 控制器）是 M（Model 模型）和 V（View 视图）的中介者，
 * 在前后端交互时起 到了中间人的作用。
 *
 * 中介者模式的四个角色
 * Mediator（抽象中介者）：定义了同事对象到中介者对象之间的接口。
 * ConcreteMediator（具体中介者）：具体的中介者对象，实现抽象方法。它需要知道所有具体的同事类，即用一个集合来管理 HashMap，并接受某个同事对象消息，完成相应的任务。
 * Colleague（抽象同事类）：抽象的同事类，各个同事类公有的方法，并声明了一些抽象方法来供子类实现，同时它维持了一个对抽象中介者类的引用，其子类可以通过该引用来与中介者通信。
 * ConcreteColleague（具体同事类）：具体的同事类，会有很多。每个同事只知道自己的行为，而不了解其他同事类的行为（方法），但他们都依赖中介者对象。
 *
 * 中介者模式中，中介者对象处于核心地位，它主要有两个作用：
 * 结构上起到中转作用。通过中介对象对关系的封装，使得具体的同事类不再需要显式地引用其他对象，它只需要通过中介者就可以完成其他同事类之间的通信。
 * 行为上起到协作作用。中介者对同事类之间的关系进行封装，同事类在不需要知道其他对象的情况下通过中介者与其他对象完成通信。在这个过程中同事类是不需要指明中介者该如何做，中介者可以根据自身的逻辑来进行协调，对同事的请求进一步处理，将同事成员之间的关系行为进行分离和封装。
 *
 *
 * 中介者模式的注意事项和细节
 * 多个类相互耦合，会形成网状结构, 使用中介者模式将网状结构分离为星型结构，进行解耦
 * 减少类间依赖，降低了耦合，符合迪米特原则
 * 中介者承担了较多的责任，一旦中介者出现了问题，整个系统就会受到影响
 * 如果设计不当，中介者对象本身变得过于复杂，这点在实际使用时，要特别注意
 *
 */
public class Test {

    public static void main(String[] args) {
        AbstractColleague collA = new ColleagueA();
        AbstractColleague collB = new ColleagueB();

        AbstractMediator am = new Mediator(collA, collB);

        System.out.println("==========通过设置A影响B==========");
        collA.setNumber(1000, am);
        System.out.println("collA的number值为："+collA.getNumber());
        System.out.println("collB的number值为A的10倍："+collB.getNumber());

        System.out.println("==========通过设置B影响A==========");
        collB.setNumber(1000, am);
        System.out.println("collB的number值为："+collB.getNumber());
        System.out.println("collA的number值为B的0.1倍："+collA.getNumber());
    }

}
