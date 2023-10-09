package com.example.homework5.calculator;

public interface Subject<T> {
    void subscribe(Observer<T> observer);

    void unsubscribe(Observer<T> observer);

    void notifyObservers();
}
