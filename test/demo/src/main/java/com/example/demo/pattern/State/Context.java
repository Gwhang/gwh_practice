package com.example.demo.pattern.State;

/**
 * Context：环境角色电灯
 */
public class Context {

    private State state;

    public String getState() {
        return state.toString();
    }

    public void setState(State state) {
        this.state = state;
    }

}
