package com.example.demo.pattern.memento;

/**
 * 备忘录模式
 * 备忘录模式（Memento Pattern）又叫做快照模式(Snapshot Pattern)，属于行为型模式。
 * 备忘录模式在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
 * 这样以后就可将该对象恢复到原先保存的状态。
 *
 * 设计模式中的备忘录模式可以类比现实中的备忘录。现实生活中的备忘录是用来记录某些要去做的事情，
 * 或者是记录已经达成的共同意 见的事情，以防忘记了。而在软件层面，备忘录模式有着相同的含义，
 * 备忘录对象主要用来记录一个对象的某 种状态，或者某些数据，当要做回退时，
 * 可以从备忘录对象里获取原来的数据进行恢复操作
 *
 * 备忘录模式的注意事项和细节
 * 给用户提供了一种可以恢复状态的机制，可以使用户能够比较方便地回到某个历史的状态
 * 实现了信息的封装，使得用户不需要关心状态的保存细节
 * 如果类的成员变量过多，势必会占用比较大的资源，而且每一次保存都会消耗一定的内存, 这个需要注意
 * 适用的应用场景：1、后悔药。 2、打游戏时的存档。 3、Windows 里的 ctri + z。 4、IE 中的后退。 4、数 据库的事务管理
 * 为了节约内存，备忘录模式可以和原型模式配合使用
 *
 *
 */
public class Client {

    public static void main(String[] args) {
        // 创建 Originator原发起
        Originator originator = new Originator();
        // 创建 CareTaker负责人
        CareTaker careTaker = new CareTaker();

        // 设置状态
        originator.setState("State 11111111111");
        originator.setState("State 22222222222");

        // 先把状态保存到备忘录,然后将备忘录添加到负责人中.
        Memento memento = originator.saveStateToMemento();
        careTaker.add(memento);

        // 设置状态
        originator.setState("State 33333333333");
        // 把状态保存到备忘录,再将备忘录添加到负责人中.
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State 44444444444");

        System.out.println("当前状态:" + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("第一次添加到备忘录的状态:" + originator.getState());

        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("第二次添加到备忘录的状态" + originator.getState());
    }


}
