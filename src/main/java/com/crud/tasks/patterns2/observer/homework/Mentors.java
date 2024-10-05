package com.crud.tasks.patterns2.observer.homework;

public class Mentors implements HWObserver {
    private final String mentorname;
    private int updateCount;
    public Mentors(String mentorname) {
        this.mentorname = mentorname;
    }
    @Override
    public void update(TaskQueue taskQueue) {
        System.out.println(mentorname + ": New messages in topic " +
                taskQueue.getTasks() + "\n" +
                        " (total: " + taskQueue.getTasks().size() + " messages)");
        updateCount++;

    }
    public String getMentorname() {
        return mentorname;
    }
    public int getUpdateCount() {
        return updateCount;
    }
}
