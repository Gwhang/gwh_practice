package com.example.demo.pattern.interpreter;

//抽象表达式(Expression)
public interface Expression {
    int interpret(Context context);
}
