package com.aronson.designpatterns.observer;

public class ObserverPatternTest {
    public static void main(String[] args) {
        Subject concreteSubject = new ConcreteSubject();
        Observer concreteObserverA = new ConcreteObserverA();
        Observer concreteObserverB = new ConcreteObserverB();
        concreteSubject.addObserver(concreteObserverA);
        concreteSubject.addObserver(concreteObserverB);
        concreteSubject.operation();
    }
}
