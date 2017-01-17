package com.javarush.test.level14.lesson06.home01;

/**
 * Created by ruslan on 17.12.16.
 */
public class BelarusianHen extends Hen {
    static int count = 12;
//    BelarusianHen(){
//        super(count);
//    }
    public int getCountOfEggsPerMonth() {
        return count;
    }
    public String getDescription() {
        return super.getDescription()
                + " Моя страна - "
                + Country.BELARUS
                + ". Я несу "
                + this.getCountOfEggsPerMonth()
                + " яиц в месяц.";
    }
}