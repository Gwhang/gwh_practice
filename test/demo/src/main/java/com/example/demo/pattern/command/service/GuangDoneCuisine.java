package com.example.demo.pattern.command.service;

import com.example.demo.pattern.command.ICook;
import com.example.demo.pattern.command.ICuisine;

public class GuangDoneCuisine implements ICuisine {

    public ICook cook;

    public GuangDoneCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }
}
