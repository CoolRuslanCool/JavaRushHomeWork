package com.javarush.test.level15.lesson12.home05;

/**
 * Created by ruslan on 21.12.16.
 */
public class SubSolution extends Solution {
    private  SubSolution(int age, String name, int weight) {}
    private  SubSolution(String name, int age, int weight) {}
    private  SubSolution(int age, int weight, String name) {}
    SubSolution(boolean in) {super(in);}
    SubSolution(String name, boolean in) {super(name,in);}
    SubSolution(boolean in, String name) {super(in,name);}
    public SubSolution() {
    }

    public SubSolution(String name) {
        super(name);
    }

    public SubSolution(int age) {
        super(age);
    }

    protected SubSolution(String name, int age) {
        super(name, age);
    }

    protected SubSolution(int age, String name) {
        super(age, name);
    }

    protected SubSolution(int weight, int age) {
        super(weight, age);
    }
    //    protected SubSolution(int age) {
//        super(age);
//    }
//
//    protected SubSolution(boolean in, String name) {
//        super(in, name);
//    }
//
//    public SubSolution(boolean in, int age) {
//        super(in, age);
//    }
//
//    public SubSolution(boolean in) {
//        super(in);
//    }
//
//    protected SubSolution(int age, String name) {
//        super(age, name);
//    }
//
//    public SubSolution() {
//
//    }
}
