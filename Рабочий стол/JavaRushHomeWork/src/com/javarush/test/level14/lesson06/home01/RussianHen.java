package com.javarush.test.level14.lesson06.home01;

/**
 * Created by ruslan on 17.12.16.
 */
public class RussianHen extends Hen {
    static int count = 30;
//    RussianHen() {
//        super(30);
//    }
    public int getCountOfEggsPerMonth() {
        return count;
    }
    public String getDescription() {
        return super.getDescription()
                + " Моя страна - "
                + Country.RUSSIA
                + ". Я несу "
                + this.getCountOfEggsPerMonth()
                + " яиц в месяц.";
    }

}