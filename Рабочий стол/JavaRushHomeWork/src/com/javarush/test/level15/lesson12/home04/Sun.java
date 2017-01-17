package com.javarush.test.level15.lesson12.home04;

/**
 * Created by ruslan on 21.12.16.
 */
public class Sun implements Planet {
    private static final Sun sun = new Sun();
    private Sun() { }
    public static Sun getInstance() {
        return sun;
    }
}
