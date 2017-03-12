package com.javarush.task.task28.task2805;

/**
 * Created by ruslan on 11.03.17.
 */
public class MyThread extends Thread {
    private static int priority;

    private int getPrio(ThreadGroup group) {
        priority++;
        if (priority>10) priority -= 10;
        if (group != null)
            if (priority>group.getMaxPriority())
                return group.getMaxPriority();
        return priority;
    }

    public MyThread() {
        setPriority(getPrio(null));
    }

    public MyThread(Runnable target) {
        super(target);
        setPriority(getPrio(null));
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority(getPrio(group));
    }

    public MyThread(String name) {
        super(name);
        setPriority(getPrio(null));
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority(getPrio(group));
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority(getPrio(null));
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority(getPrio(group));
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority(getPrio(group));
    }
}
