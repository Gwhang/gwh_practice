package com.example.demo.pattern.command.service;

import com.example.demo.pattern.command.ICook;
import com.example.demo.pattern.command.ICuisine;

public class JiangSuCuisine implements ICuisine {

    private ICook cook;

    public JiangSuCuisine(ICook cook) {
        this.cook = cook;
    }

    public void cook() {
        cook.doCooking();
    }
}
