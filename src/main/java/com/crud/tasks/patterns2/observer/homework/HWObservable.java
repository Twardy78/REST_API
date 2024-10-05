package com.crud.tasks.patterns2.observer.homework;

public interface HWObservable {
    void registerObserver(HWObserver hwObserver);
    void notifyObservers();
    void removeObserver(HWObserver hwObserver);
}
