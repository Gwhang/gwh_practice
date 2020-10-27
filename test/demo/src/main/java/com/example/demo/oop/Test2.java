package com.example.demo.oop;

/**
 * 多态
 * 多态： 其实就是一种思想
 * 从代码层次来说 ： 父类引用引用子类对象，且父类和子类有同名的覆盖方法（重写），通过父类的引用来调用同名的覆盖方法的时候，他有可能会表现出不同的行为，把表现出这种不同行为的过程，这种思想我们叫做多态。
 * 向上转型：父类的引用，引用子类对象
 * ① 直接赋值 ② 方法的传参 ③ 返回值
 * 动态绑定：
 * 1、父类的引用，引用子类的对象（向上转型）
 * 2、通过父类的引用，调用子类和父类重写的那个方法
 *
 * 使用多态的好处：
 * 1、类调用者对类的使用成本进一步降低.
 * 封装是让类的调用者不需要知道类的实现细节，多态能让类的调用者连这个类的类型是什么都不必知道, 只需要知道这个对象具有某个方法即可.
 * 因此, 多态可以理解成是封装的更进一步, 让类调用者对类的使用成本降低
 * 2、能够降低代码的 “圈复杂度”, 避免使用大量的 if - else
 * （圈复杂度：一段代码中条件语句和循环语句出现的次数）
 * 3、可扩展能力更强
 *
 */
public class Test2 {

    //构造器
    public static void drawMap(Shape shape) {
        shape.draw(); //多态的核心
    }

    public static void main(String[] args) {
        Shape shape1 = new Cycle();
        Shape shape2 = new Rect();
        drawMap(shape1);
        drawMap(shape2);
    }

}

class Shape {
    public void draw() {
    }
}

class Cycle extends Shape {
    @Override
    public void draw() {
        System.out.println("画一个⚪圈!");
    }
}
class Rect extends Shape {
    @Override
    public void draw() {
        System.out.println("画一个♦");
    }
}



