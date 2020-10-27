package com.example.demo.pattern.State;

/**
 * LightOn：具体的开灯状态
 */
public class LightOn implements State {

    @Override
    public void handle(Context context) {
        context.setState(this);
        System.out.println("开灯了...");
    }

    @Override
    public String toString() {
        return "开灯状态";
    }


}
