package com.javarush.test.level14.lesson08.home05;

/**
 * Created by ruslan on 18.12.16.
 */
public class Computer {
    private Monitor monitor;
    private Mouse mouse;
    private Keyboard keyboard;

    public Computer() {
        monitor = new Monitor();
        mouse = new Mouse();
        keyboard = new Keyboard();
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Monitor getMonitor() {

        return monitor;
    }

    public Mouse getMouse() {

        return mouse;
    }
}
