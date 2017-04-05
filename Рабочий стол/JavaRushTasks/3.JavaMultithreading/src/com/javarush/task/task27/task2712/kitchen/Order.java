package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by ruslan on 18.03.17.
 */
public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public int getTotalCookingTime() {
        int res = 0;
        for (Dish dish: dishes) {
            res += dish.getDuration();
        }
        return res;
    }

    public boolean isEmpty() {
        if (dishes.size() == 0)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        if (dishes.size() == 0)
            return "";
        return "Your order: " + dishes + " of " + tablet;
    }
}
