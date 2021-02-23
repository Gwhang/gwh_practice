package com.example.demo.clazz;

import jdk.nashorn.internal.runtime.logging.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * java class 反射机制
 *
 * JAVA 的反射机制是在运行状态中，对于任意一个实体类，都能够知道这个类的所有属性和方法，对于任意一个对象，都能够调用它的任意方法和属性；这种动态
 * 获取信息及动态调用对象方法的功能称为 java 语言的反射机制
 * 框架：半成品软件，可以在框架的基础上进行开发，而框架本身不能运行
 * 反射：反射是框架的灵魂，将类的各个组成部分封装为其他对象
 * 反射的好处：可以在程序运行的过程中去操作对象，字节码文件，不需要重新编译，提高程序扩展性，复用性，解耦
 *
 * Class 代表类的实体，在运行的java 应用程序中表示类和接口
 *
 * 注解
 * 注释：用于文字描述，给程序员看的
 * 定义：用来说明程序的一个标识，给计算机看的，注解也叫元数据，是一种代码级别的说明，它是jdk1.5之后引入的一个特性，是一种特殊的接口，可以用在字段，类
 * 方法，包，参数等上面
 * 注意：注解本身并没有任何功能，仅仅起到一个标识性作用，我们是通过反射获取到注解，再根据是否有这个注解，注解中的一些属性，去判断执行那种业务逻辑
 *
 * 作用分类：
 *  编写文档：通过代码里的注解标识去生成API文档（Swagger）
 *  代码分析：通过注解去对代码进行逻辑上的分析（通过反射去操作业务）
 *  编译检查：通过编辑器实现基本的编译检查（Override,SuppressWarning）
 *
 * Jdk中预定的一些注解
 *  Override:检测注解标识的方法是否是继承自父类
 *  Deprecated:标识该内容以及过时，后续的版本可能会进行移除
 *  SuppressWarnings:压制警告，指定警告级别，这个级别下的警告全都不显示
 *
 *  自定义注解：
 *  在实际开发中，可能存在一些代码及其复杂或者复用性很低的业务逻辑，比如：导出Excel,缓存,将返回值转json,事务等等，这个时候就可以使用自定义注解
 *
 *  格式
 *  元注解
 *  public @interface 注解名称{
 *      属性列表;
 *  }
 *
 *  本质就是一个接口，该接口继承 Annotation接口
 *
 *  属性
 *  本质：接口中的抽象方法
 *  属性只能是以下几种数据类型：
 *  1.基本数据类型
 *  2.String
 *  3.枚举
 *  4.注解
 *  5.以上类型的数组
 *  如果定义了属性，在使用的时候需要给属性赋值，如果有默认值则可以不附值，如果只有1个属性需要赋值，并且这个属性叫Value 那么就可以省略属性名，数组赋值的时候，用{}包裹，如果
 *  只有1个值，那么大括号可以省略
 *
 *@Target 表示该注解用于什么地方，可能的值在枚举类 ElemenetType 中，包括：
 *
 *      ElemenetType.CONSTRUCTOR-----------------------------构造器声明
 *      ElemenetType.FIELD ----------------------------------域声明（包括 enum 实例）
 *      ElemenetType.LOCAL_VARIABLE------------------------- 局部变量声明
 *      ElemenetType.METHOD ---------------------------------方法声明
 *      ElemenetType.PACKAGE --------------------------------包声明
 *      ElemenetType.PARAMETER ------------------------------参数声明
 *      ElemenetType.TYPE----------------------------------- 类，接口（包括注解类型）或enum声明
 *
 *
 * @Retention 表示在什么级别保存该注解信息。可选的参数值在枚举类型 RetentionPolicy 中，包括：
 *
 *      RetentionPolicy.SOURCE-------------注解将被编译器丢弃
 *      RetentionPolicy.CLASS -------------注解在class文件中可用，但会被VM丢弃
 *      RetentionPolicy.RUNTIME ---------VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。
 *
 *
 * @Documented 将此注解包含在 javadoc 中 ，它代表着此注解会被javadoc工具提取成文档。在doc文档中的内容会因为此注解的信息内容不同而不同。相当与@see,@param 等。
 *
 * @Inherited 允许子类继承父类中的注解。
 *
 *
 */
public class Test {

    public static void main(String[] args) throws Exception {
        // 获取一个Class
        Class<User> userClass=User.class;
        // getName 获取类的完整路径的名字
        System.out.println(userClass.getName());
        // 获取不带包名的全类名
        System.out.println(userClass.getSimpleName());
        // 获取class 方法2 根据类名获取类的对象
        Class<?> userClass2=Class.forName("com.example.demo.clazz.User");
        System.out.println(userClass2.getSimpleName());

        // 获取class 方法3
        User user=new User();
        Class<? extends User> userClass3 = user.getClass();
        System.out.println(userClass3.getSimpleName());
        System.out.println("-------------------------------------------");


        Class<User> userClass1 = User.class;
        // 获取类的包名，注意这里的包名是包含 package的
        System.out.println(userClass1.getPackage());

        // 创建实例
        User user1 = userClass1.newInstance();
        System.out.println(user1);

        // 获得类加载器
        ClassLoader classLoader = userClass.getClassLoader();

        // 获取该类中的公共类
        Class<?>[] classes = userClass1.getClasses();
        for (Class<?> cls:classes){
            System.out.println(cls.getSimpleName());
        }
        System.out.println("---------------------------------");
        // 获取该类中的类 包括非公共类
        Class<?>[] declaredClasses = userClass1.getDeclaredClasses();
        for (Class<?> cls:declaredClasses){
            System.out.println(cls.getSimpleName());
        }
        System.out.println("---------------------------------");

        // 把传递的对象转换成代表其子类的对象
        Class<Brid> brid = Brid.class;
        Class<? extends Animal> aClass = brid.asSubclass(Animal.class);
        System.out.println(aClass.getName());

        // 获得当前继承的父类的名字
        Class<? super Brid> superclass = brid.getSuperclass();
        System.out.println(superclass.getName());
        // 获得当前类实现的类或是接口
        Class<?>[] interfaces = brid.getInterfaces();
        for (Class<?> inf:interfaces){
            System.out.println(inf.getName());
        }
        System.out.println("---------------------------------");
        Class<User> userField=User.class;
        // 获取类中的所有公共字段
        Field[] fields = userField.getFields();
        for (Field fid:fields){
            System.out.println(fid.getName());
        }
        // 获取类中某个公共的字段
        Field name = userField.getField("idNumber");
        System.out.println(name.getName());
        System.out.println("----------------------");
        // 获取类中的所有字段
        Field[] declaredFields = userField.getDeclaredFields();
        for (Field fid:declaredFields){
            System.out.println(fid.getName());
        }
        System.out.println("--------------");
        // 获取类中某个字段
        Field name1 = userField.getDeclaredField("name");
        System.out.println(name1.getName());

        System.out.println("---------------------------------");
        // 获取指定的注解 可以根据此来判断，根据注解是否存在，去执行相关的逻辑
        Logger annotation = userField.getAnnotation(Logger.class);
        System.out.println(annotation);
        System.out.println("---------------------------------");
        // 获取类中的所有注解
        Annotation[] annotations = userField.getAnnotations();
        for (Annotation an:annotations){
            System.out.println(an);
        }
        System.out.println("---------------------------------");
        // 获取该类中所有公共构造方法
        Constructor<?>[] constructors = userField.getConstructors();
        for (Constructor<?> con:constructors) {
            System.out.println("获取到"+con.getParameterCount()+"个公共构造方法"+con.getName());
        }
        System.out.println("---------------------------------");
        // 获取类中所有构造方法
        Constructor<?>[] declaredConstructors = userField.getDeclaredConstructors();
        for (Constructor<?> con:declaredConstructors) {
            System.out.println(con.getName());
        }
        System.out.println("---------------------------------");
        // 获取该类中与参数类型匹配的公有构造方法 默认是无参构造方法
        Constructor<User> constructor = userField.getConstructor();
        System.out.println(constructor);
        System.out.println("---------------------------------");
        // 获取该类中与参数类型匹配的构造方法 注意参数个数应与构造方法一致
        Constructor<User> declaredConstructor = userField.getDeclaredConstructor(String.class, Integer.class,String.class);
        System.out.println(declaredConstructor);
        System.out.println("---------------------------------");
        // 获取该类所有公有的方法
        Method[] methods = userField.getMethods();
        for (Method method:methods){
            System.out.println(method);
        }
        System.out.println("---------------------------------");
        // 获取该类中的某个公有方法 参数 方法名称 和 方法的参数
        Method method1 = userField.getMethod("getName");
        System.out.println(method1);
        System.out.println("---------------------------------");
        // 获取该类中的所有方法
        Method[] declaredMethods = userField.getDeclaredMethods();
        for (Method method:methods){
            System.out.println(method);
        }
        System.out.println("---------------------------------");
        // 获取该类中的某个方法  根据名称和参数类型
        Method method2 = userField.getDeclaredMethod("setName", String.class);
        System.out.println(method2);
        System.out.println("---------------------------------");
        // 如果是注解类型则返回true
        System.out.println(Logger.class.isAnnotation());
        System.out.println(User.class.isAnnotation());
        // 如果是接口则返回true
        System.out.println(MyAbility.class.isInterface());
        System.out.println(User.class.isInterface());
        // 如果是枚举则返回true
        System.out.println(User.class.isEnum());
        // 如果是数组则返回 true
        System.out.println(User.class.isArray());
        // 如果 obj是该类的实例则返回true
        System.out.println(User.class.isInstance(user));

    }

}

//@Logger
//如果属性名称是Value 时可以直接赋值
@MyAnnotation("我是用户类")
class User{

    @MyAnnotation("张三")
    private String name;
    protected  Integer age;
    public String idNumber;

    public User() {
    }

    private static class Bike{
        private String name;
    }


    public static class Clothes{
        private String name;
    }

    protected User(String name, Integer age, String idNumber) {
        this.name = name;
        this.age = age;
        this.idNumber = idNumber;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Integer getAge() {
        return age;
    }

    protected void setAge(Integer age) {
        this.age = age;
    }

    private String getIdNumber() {
        return idNumber;
    }

    private void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
class Animal{

    private String name;

    private String age;

    private String ability;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}

class Brid extends Animal implements MyAbility{

    @Override
    public void method() {
        System.out.println("小鸟的本领是偷东西");
    }
}

interface MyAbility{

    void method();

}