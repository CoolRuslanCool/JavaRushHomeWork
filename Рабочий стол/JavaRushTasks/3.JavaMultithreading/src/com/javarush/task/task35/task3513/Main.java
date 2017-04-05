package com.javarush.task.task35.task3513;

import javax.swing.*;

/**
 * Created by ruslan on 26.03.17.
 */
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        JFrame frame = new JFrame();
        frame.setTitle("2048");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(450,500);
        frame.setResizable(false);
        frame.add(controller.getView());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
