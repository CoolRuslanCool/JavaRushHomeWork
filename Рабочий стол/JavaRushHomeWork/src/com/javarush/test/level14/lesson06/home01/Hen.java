package com.javarush.test.level14.lesson06.home01;

/**
 * Created by ruslan on 17.12.16.
 */
abstract class Hen {

//    static int count;
//    Hen(int count) {
//        this.count = count;
//    }
    abstract int getCountOfEggsPerMonth();
    String getDescription() {
        return "Я курица.";
    }
}
