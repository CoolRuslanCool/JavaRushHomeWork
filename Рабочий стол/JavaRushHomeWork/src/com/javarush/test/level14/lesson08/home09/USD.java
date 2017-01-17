package com.javarush.test.level14.lesson08.home09;

/**
 * Created by ruslan on 18.12.16.
 */
public class USD extends Money {
    public USD(double amount) {
        super(amount);
    }

    @Override
    public String getCurrencyName() {
        return "USD";
    }
}
