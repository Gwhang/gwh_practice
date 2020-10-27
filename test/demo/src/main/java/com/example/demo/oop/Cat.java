package com.example.demo.oop;

public class Cat extends Animal {

    //移动速度
    private String speed;

    public Cat(String speed) {
        this.speed = speed;
    }

    public Cat() {
        super();
    }


    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "speed='" + speed + '\'' +
                '}';
    }
}
