package com.example.demo.pattern.State;

/**
 * LightOff：具体的关灯状态
 */
public class LightOff implements State{

    @Override
    public void handle(Context context) {
        context.setState(this);
        System.out.println("关灯了...");
    }

    @Override
    public String toString() {
        return "关灯状态";
    }

}
