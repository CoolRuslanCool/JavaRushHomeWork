package com.javarush.test.level18.lesson08.task01;


/**
 * Created by ruslan on 03.01.17.
 */
public class DecoratorMyRunnableImpl implements Runnable {
    public Runnable runnable;
    public DecoratorMyRunnableImpl(Runnable runnable) {
        this.runnable = runnable;
    }
    @Override
    public void run() {
        System.out.println("in DecoratorMyRunnableImpl");
        runnable.run();
    }


    public static class RunnableImpl implements Runnable {
        @Override
        public void run() {
            System.out.println("in MyRunnableImpl");
        }
    }
}
