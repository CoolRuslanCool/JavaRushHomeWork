package com.javarush.task.task29.task2912;

/**
 * Created by ruslan on 18.03.17.
 */
public abstract class AbstractLogger implements Logger {
    int level;
    Logger next;

    public AbstractLogger(int level) {
        this.level = level;
    }

    @Override
    public void setNext(Logger next) {
        this.next = next;
    }

    @Override
    public void inform(String message, int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }
}
