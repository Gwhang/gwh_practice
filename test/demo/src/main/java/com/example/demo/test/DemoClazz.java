package com.example.demo.test;

/**
 * 双检测锁（double check lock）单例模式，通俗来讲就是需要进行两次进行非空检测，并且需要加锁进行同步控制，
 * 是线程安全的单例模式实现方式之一
 * 一、构造方法私有化
 * 构造方法必须私有化，我们学习java时都只见过public修饰的构造方法，但是构造方法是允许用private修饰的。
 * 构造方法非私有化，会导致程序调用者可以私自调用构造方法实例化对象，从而破坏单例模式的特性。
 *二、static关键字不能漏
 * getInstance()方法用来获取单例，由于对象在类加载进来时是没有实例对象存在的，所以只能通过static方法来获取类实例。
 * 同理static方法中访问到的属性均需要为类属性（static修饰的属性），所以用来存放实例对象引用的属性instance用static修饰。
 *三、instance需要private修饰
 * instance只有将访问权限控制在本类方法中，才能保证逻辑的正确，如果用户直接获取instance，可能会获取到一个null值。
 *
 * 四、instance需要volatile修饰
 * 在 JVM的内存模型中，每个线程读取和操作对象的属性时，并不是直接在内存中操作，而是生成一个副本进行存取，volatile关键字可以保证现场之间对该变量保持可见性，即线程A对变量c的修改，线程B在下次读取变量c时能立马感知到。
 * 尽管有关键字synchronized关键字，如果不加上volatile关键字，可能会导致在并发场景下线程各自生成了实例对象在各自的线程工作空间里。
 *
 */
public class DemoClazz {
    private static volatile DemoClazz instance;
    private DemoClazz(){}

    /**
     * getInstance方法会进行两次判空操作；第一次，判断是否实例化了，有实例对象则直接返回，不需要其他操作。
     * 如果没有实例化，则说明是程序第一次去获取实例对像，会进行一次加锁操作，只允许一个线程进入方法，
     * 进入方法之后的判空操作是为了仅允许第一个进入的线程进行实例化，其他线程不允许实例化。
     * 因为实例化操作仅需要进行一次同步，所以可以用第一次判空操作来进行避免。至于第二次判空操作，则是为了保证单例。
     * @return
     */
    public static DemoClazz getInstance(){
        if(instance == null){
            synchronized(DemoClazz.class){
                if(instance == null){
                    instance = new DemoClazz();
                }
            }
        }
        return instance;
    }
}
