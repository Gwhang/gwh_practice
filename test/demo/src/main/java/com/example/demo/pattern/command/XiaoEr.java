package com.example.demo.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 调用者 小二
 *
 * 在调用者的具体实现中，提供了菜品的添加和菜单执行烹饪。这个过程是命令模式的具体调⽤用，
 * 通过外部将菜品和厨师传递进来⽽进行具体的调用。
 */
public class XiaoEr {

    private List<ICuisine> cuisineList = new ArrayList<ICuisine>();

    /**
     * 添加订单
     * @param cuisine
     */
    public void order(ICuisine cuisine) {
        cuisineList.add(cuisine);
    }

    /**
     * 下单
     */
    public synchronized void placeOrder() {
        for (ICuisine cuisine : cuisineList) {
            cuisine.cook();
        }
        cuisineList.clear();
    }

}
