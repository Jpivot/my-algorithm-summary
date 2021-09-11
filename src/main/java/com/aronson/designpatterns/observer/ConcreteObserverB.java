package com.aronson.designpatterns.observer;

public class ConcreteObserverB implements Observer {
    @Override
    public void observe() {
        System.out.println("B观察者在观察");
    }
}
