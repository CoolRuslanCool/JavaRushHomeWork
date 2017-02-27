package com.javarush.task.task01.task0109;

import java.util.concurrent.TimeUnit;

/**
 * Created by ruslan on 22.02.17.
 */
public class Operation {
    public static void main(String[] args) {
        final Account a = new Account(1000);
        final Account b = new Account(2000);

        new Thread(new Runnable() {
            public void run() {
                transfer(a, b, 500);
            }
        }).start();
        transfer(b, a, 300);
    }

    static void transfer(Account a, Account b, int amount) {
        try {
            if (a.getLock().tryLock(5, TimeUnit.SECONDS)) {
                try {
                    if (a.getBallans() < amount) throw new RuntimeException();
                    if (b.getLock().tryLock(5, TimeUnit.SECONDS)) {
                        try {
                            a.withdraw(amount);
                            System.out.println("out complit");
                            b.deposit(amount);
                            System.out.println("in accept");
                        } finally {
                            b.getLock().unlock();
                        }
                    } else {
                        a.inkFailCount();
                        b.inkFailCount();
                    }
                } finally {
                    a.getLock().unlock();
                }

            } else {
                System.out.println("no empty!");
                a.inkFailCount();
            }
        } catch (Exception e) {
        }
    }
}
