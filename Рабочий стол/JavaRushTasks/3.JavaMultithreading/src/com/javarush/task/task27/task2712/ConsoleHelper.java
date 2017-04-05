package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 18.03.17.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        writeMessage(Dish.allDishesToString());
        List<Dish> dishes = new LinkedList<>();
        String str;
        while (!(str = readString()).equals("exit")) {
            try {
                dishes.add(Dish.valueOf(str));
            } catch (IllegalArgumentException e) {
                writeMessage("Такого блюда нет.");
            }
        }
        return dishes;
    }
}
