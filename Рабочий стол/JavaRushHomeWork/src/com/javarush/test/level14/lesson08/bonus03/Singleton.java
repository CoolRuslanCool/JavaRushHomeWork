package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by ruslan on 18.12.16.
 */
public class Singleton {
    private static final Singleton vas = new Singleton();
    private String name;
    static int count;
    private Singleton(){
    }
    static Singleton getInstance() {
        return vas;
    }
}
