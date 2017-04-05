package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

/**
 * Created by ruslan on 18.03.17.
 */
public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        return new String(Arrays.deepToString(Dish.values()));
    }
}
