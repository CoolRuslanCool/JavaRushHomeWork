package com.javarush.test.level14.lesson06.home01;

/**
 * Created by ruslan on 17.12.16.
 */
public class UkrainianHen extends Hen {
    static int count = 20;
//    UkrainianHen() {
//        super(20);
//    }
    public int getCountOfEggsPerMonth() {
        return count;
    }
    public String getDescription() {
        return super.getDescription()
                + " Моя страна - "
                + Country.UKRAINE
                + ". Я несу "
                + this.getCountOfEggsPerMonth()
                + " яиц в месяц.";
    }
}