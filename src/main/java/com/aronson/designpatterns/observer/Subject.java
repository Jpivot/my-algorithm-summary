package com.aronson.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    public abstract void operation();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

}
