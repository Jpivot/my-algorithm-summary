package com.aronson.designpatterns.factory;

public class HouseGood implements Good {

    @Override
    public void produce() {
        System.out.println("生产居住产所");
    }
}
