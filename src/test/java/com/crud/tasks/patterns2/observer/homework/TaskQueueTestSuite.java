package com.crud.tasks.patterns2.observer.homework;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskQueueTestSuite {
    @Test
    public void testUpdate() {
        // Given
        TaskQueue lesson1queue = new HWQueueOne();
        TaskQueue lesson2queue = new HWQueueTwo();
        Mentors tomRichard = new Mentors("Tom Richard");
        Mentors stephanFord= new Mentors("Stephan Ford");
        lesson1queue.registerObserver(tomRichard);
        lesson2queue.registerObserver(stephanFord);

        // When
        lesson1queue.addTask("Home work #1");
        lesson1queue.addTask("Home work #2");
        lesson2queue.addTask("Home work #3");
        lesson2queue.addTask("Home work #4");
        lesson1queue.addTask("Home work #5");
        lesson1queue.addTask("Home work #6");
        // Then
        assertEquals(4, tomRichard.getUpdateCount());
        assertEquals(2, stephanFord.getUpdateCount());
    }
}
