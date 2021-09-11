package com.aronson.designpatterns.factory;

public class LifeGood implements Good {
    @Override
    public void produce() {
        System.out.println("生产日用品");
    }
}
