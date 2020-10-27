package com.example.demo.pattern.memento;

/**
 * Originator（原发器）：需要保存状态的对象。
 */
public class Originator {

    // 状态信息
    private String state;

    /**
     * 获取状态
     * @return 返回状态信息
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
     * @param state 状态信息
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 将状态信息保存到Memento备忘录
     * @return Memento 对象
     */
    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    /**
     * 通过备忘录从备忘录获取状态信息
     * @param memento 备忘录
     */
    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }

}
