package com.javarush.test.level15.lesson12.home04;

/**
 * Created by ruslan on 21.12.16.
 */
public class Earth implements Planet {
    private static final Earth earth = new Earth();
    private Earth(){}
    public static Earth getInstance() {
        return earth;
    }
}
