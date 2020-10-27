package com.example.demo.pattern.interpreter;

public class Div implements Expression{

    private Expression left, right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }


    @Override
    public int interpret(Context context) {
        return left.interpret(context) / right.interpret(context);
    }
}
