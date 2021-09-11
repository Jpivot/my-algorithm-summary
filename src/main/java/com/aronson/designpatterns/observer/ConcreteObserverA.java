package com.aronson.designpatterns.observer;

public class ConcreteObserverA implements Observer {
    @Override
    public void observe() {
        System.out.println("A观察者观察");
    }
}
