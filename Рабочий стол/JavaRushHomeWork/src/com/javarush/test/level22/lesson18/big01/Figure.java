package com.javarush.test.level22.lesson18.big01;

/**
 * Created by ruslan on 02.02.17.
 */
public class Figure {
    private int x;
    private int y;
    private int[][] matrix;

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int[][] getMatrix() { return matrix; }

    public void left() {
        x--;
        if (!isCurrentPositionAvailable()) x++;
    }

    public void right() {
        x++;
        if (!isCurrentPositionAvailable()) x--;
    }

    public void down() {
        y++;

    }

    public void up() {
        y--;
    }

    public void downMaximum() {
        while (isCurrentPositionAvailable()) {
            y++;
        }
        y--;
    }

    public void rotate() {
        int[][] ma = new int[3][3];
        for (int yy = 0;yy<3;yy++) {
            for (int xx = 0;xx<3;xx++) {
                ma[yy][xx] = matrix[xx][yy];
            }
        }
        matrix = ma;
    }

    public boolean isCurrentPositionAvailable() {
        Field field = Tetris.game.getField();
        for (int yy = 0;yy < 3;yy++) {
            for (int xx = 0;xx < 3;xx++) {

                if (matrix[yy][xx] == 1) {
                    //if (yy+y >= field.castToHasHeight()) return false;
                    Integer d = field.getValue(x+xx, y+yy);
                    if (d == null || d == 1) return false;
                }
            }
        }
        return true;
    }

    public void landed() {
        Field field = Tetris.game.getField();
        for (int yy = 0;yy<3;yy++) {
            for (int xx = 0;xx<3;xx++) {
                if (matrix[yy][xx] == 1)
                    field.setValue(x+xx, y+yy, 1);
            }
        }
        Tetris.game.setField(field);
    }
}
