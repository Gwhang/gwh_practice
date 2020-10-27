package com.example.demo.pattern.memento;

/**
 * Memento（备忘录）：备忘录对象，负责保存好记录，即Originator内部状态。
 */
public class Memento {
    // 状态信息
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    /**
     * 获取状态信息
     * @return 状态信息
     */
    public String getState() {
        return state;
    }
}
