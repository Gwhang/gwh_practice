package com.example.demo.pattern.mediator;

/**
 * 具体的同事类
 */
public class ColleagueB extends AbstractColleague {

    @Override
    public int getNumber() {
        return super.getNumber();
    }

    @Override
    public void setNumber(int number) {
        super.setNumber(number);
    }

    @Override
    public void setNumber(int number, AbstractMediator am) {
        this.number = number;
        am.BaffectA();
    }
}
