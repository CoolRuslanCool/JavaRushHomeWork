package com.javarush.test.level15.lesson12.home04;

/**
 * Created by ruslan on 21.12.16.
 */
public class Moon implements Planet {
    private static final Moon moon = new Moon();
    private Moon(){}
    public static Moon getInstance() {
        return moon;
    }
}
