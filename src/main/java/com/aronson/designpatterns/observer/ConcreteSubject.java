package com.aronson.designpatterns.observer;

public class ConcreteSubject extends Subject {

    @Override
    public void operation() {
        observers.forEach(observer -> {
            observer.observe();
        });
    }


}
