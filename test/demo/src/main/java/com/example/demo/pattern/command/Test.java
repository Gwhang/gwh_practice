package com.example.demo.pattern.command;

import com.example.demo.pattern.command.service.GuangDoneCuisine;
import com.example.demo.pattern.command.service.GuangDongCook;
import com.example.demo.pattern.command.service.JiangSuCook;
import com.example.demo.pattern.command.service.JiangSuCuisine;

/**
 *什么是命令行模式？
 * 命令模式（Command Pattern）是一种数据驱动的设计模式，它属于行为型模式。
 * 请求以命令的形式包裹在对象中，并传给调用对象。
 * 调用对象寻找可以处理该命令的合适的对象，并把该命令传给相应的对象，该对象执行命令。
 *
 * 在这个设计模式的实现过程中有如下几个比较重要的点；
 * 1. 抽象命令类；声明执⾏命令的接⼝和⽅法
 * 2. 具体的命令实现类；接⼝类的具体实现，可以是⼀组相似的行为逻辑
 * 3. 实现者；也就是为命令做实现的具体实现类
 * 4. 调⽤用者；处理理命令、实现的具体操作者，负责对外提供命令服务
 */
public class Test {

    public static void main(String[] args) {
        // 菜系 + 厨师；⼴广东（粤菜）、江苏（苏菜）、⼭山东（鲁菜）、四川（川菜）
        ICuisine guangDoneCuisine = new GuangDoneCuisine(new GuangDongCook());
        JiangSuCuisine jiangSuCuisine = new JiangSuCuisine(new JiangSuCook());
        /*ShanDongCuisine shanDongCuisine = new ShanDongCuisine(new
                ShanDongCook());
        SiChuanCuisine siChuanCuisine = new SiChuanCuisine(new SiChuanCook());*/
        // 点单
        XiaoEr xiaoEr = new XiaoEr();
        xiaoEr.order(guangDoneCuisine);
        xiaoEr.order(jiangSuCuisine);
//        xiaoEr.order(shanDongCuisine);
//        xiaoEr.order(siChuanCuisine);
        // 下单
        xiaoEr.placeOrder();
    }

}
