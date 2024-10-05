package com.crud.tasks.patterns2.observer.homework;

import java.util.*;

public class TaskQueue implements HWObservable {
    private final List<HWObserver> hwObservers;
    private final List<String> tasks;
    private final String name;

    public TaskQueue(String name) {
        this.hwObservers = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.name = name;
    }
    @Override
    public void registerObserver(HWObserver hwObserver) {
        hwObservers.add(hwObserver);
    }
    @Override
    public void notifyObservers() {
        for (HWObserver hwObserver : hwObservers) {
            hwObserver.update(this);
        }
    }
    public void removeObserver(HWObserver hwObserver) {
        hwObservers.remove(hwObserver);
    }
    public void addTask(String task) {
        tasks.add(task);
        notifyObservers();
    }
    public List<String> getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }
}
