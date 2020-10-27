package com.example.demo.pattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Caretaker（负责人）：负责人对象，负责保存多个备忘录对象，使用集合管理，提高效率。
 * 如果需要保存多个Originator对象的不同状态，可以使用 HashMap<String, 集合>来管理。
 */
public class CareTaker {

    /**
     * 备忘录列表
     */
    private List<Memento> mementoList = new ArrayList<Memento>();

    /**
     * 添加备忘录到备忘录列表
     * @param memento 备忘录
     */
    public void add(Memento memento) {
        mementoList.add(memento);
    }

    /**
     * 从备忘录列表获取备忘录
     * @param index 备忘录的索引
     * @return 备忘录
     */
    public Memento get(int index) {
        return mementoList.get(index);
    }

}
