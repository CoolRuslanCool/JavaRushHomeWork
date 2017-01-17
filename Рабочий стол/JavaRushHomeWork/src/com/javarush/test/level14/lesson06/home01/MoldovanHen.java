package com.javarush.test.level14.lesson06.home01;

/**
 * Created by ruslan on 17.12.16.
 */
public class MoldovanHen extends Hen {
    static int count = 15;
//    MoldovanHen() {
//        super(15);
//    }
    public int getCountOfEggsPerMonth() {
        return count;
    }
    public String getDescription() {
        return super.getDescription()
                + " Моя страна - "
                + Country.MOLDOVA
                + ". Я несу "
                + this.getCountOfEggsPerMonth()
                + " яиц в месяц.";
    }
}
