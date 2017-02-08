package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by ruslan on 30.01.17.
 */
public class Hippodrome {
    public static Hippodrome game;
    public ArrayList<Horse> horses = new ArrayList<>();

    public static void main(String[] args) {
        game = new Hippodrome();
        Horse horse1 = new Horse("111", 3, 0);
        Horse horse2 = new Horse("222", 3, 0);
        Horse horse3 = new Horse("333", 3, 0);
        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);
        game.run();
        game.printWinner();
    }

    public void run() {
        for (int i = 0;i<100;i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printWinner() {
        System.out.printf("Winner is %s!", getWinner().getName());
    }

    public Horse getWinner() {
        Horse horse = getHorses().get(0);

        for (Horse horse1: getHorses())
            if (horse1.getDistance() > horse.getDistance()) horse = horse1;

        return horse;
    }

    public void move() {
        for (Horse horse: getHorses())
            horse.move();
    }

    public void print() {
        for (Horse horse: getHorses())
            horse.print();
        System.out.println();
        System.out.println();
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }
}
