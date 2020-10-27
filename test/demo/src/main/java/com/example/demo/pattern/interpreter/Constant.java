package com.example.demo.pattern.interpreter;

//终结符表达式(Terminal Expression)
public class Constant implements Expression {

    private int i;

    public Constant(int i) {
        this.i = i;
    }

    @Override
    public int interpret(Context context) {
        return i;
    }
}
