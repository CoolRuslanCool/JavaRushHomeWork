package com.javarush.task.task01.task0109;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by ruslan on 22.02.17.
 */
public class Transfer implements Callable<Boolean> {
    private Account a;
    private Account b;
    private int amount;
    public static CountDownLatch latch;

    public Transfer(Account a, Account b, int amount, int countDown) {
        this.a = a;
        this.b = b;
        this.amount = amount;
        this.latch = new CountDownLatch(countDown);
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("latch " + latch.getCount());
        latch.await();

        if (a.getLock().tryLock(0, TimeUnit.SECONDS)) {
            if (b.getLock().tryLock(1, TimeUnit.SECONDS)) {
                    if (a.getBallans() < amount) throw new Exception();
//                    Thread.sleep((int) (Math.random() * 100));
                    a.withdraw(amount);
                    b.deposit(amount);
            } else {
                a.inkFailCount();
                b.inkFailCount();
                return false;
            }
        } else {
            a.inkFailCount();
            return false;
        }
        return true;
    }
}
