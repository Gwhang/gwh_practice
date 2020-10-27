package com.example.demo.pattern.interpreter;

public class Variable implements Expression {

    public Variable() {
    }
    @Override
    public int interpret(Context context) {
        return context.LookupValue(this);
    }
}
