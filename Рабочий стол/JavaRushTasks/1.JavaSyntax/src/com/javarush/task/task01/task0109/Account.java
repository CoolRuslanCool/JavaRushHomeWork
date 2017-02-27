package com.javarush.task.task01.task0109;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ruslan on 22.02.17.
 */
public class Account {
    private int ballans;
    private final Lock lock;
    private AtomicInteger failCount = new AtomicInteger();

    public Account(int ballans) {
        lock = new ReentrantLock();
        this.ballans = ballans;
    }

    public int getFailCount() {
        return failCount.intValue();
    }

    public void inkFailCount() { failCount.incrementAndGet(); }

    public Lock getLock() {
        return lock;
    }

    public void withdraw(int out) {
        ballans -= out;
    }

    public void deposit(int in) {
        ballans += in;
    }

    public int getBallans() {
        return ballans;
    }
}
