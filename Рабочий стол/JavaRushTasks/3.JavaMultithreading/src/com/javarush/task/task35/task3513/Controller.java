package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by ruslan on 26.03.17.
 */
public class Controller extends KeyAdapter {
    private View view;
    private Model model;
    private static final int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public int getScore() {
        return model.score;
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public void resetGame() {
        model.score = 0;
        view.isGameWon = false;
        view.isGameLost = false;
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 27)
            resetGame();
        else if (!model.canMove())
            view.isGameLost = true;
        else if (!view.isGameLost && !view.isGameWon) {
            switch (code) {
                case KeyEvent.VK_A:
                    model.autoMove();
                    break;
                case KeyEvent.VK_R:
                    model.randomMove();
                    break;
                case KeyEvent.VK_Z:
                    model.rollback();
                    break;
                case KeyEvent.VK_LEFT:
                    model.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    model.right();
                    break;
                case KeyEvent.VK_UP:
                    model.up();
                    break;
                case KeyEvent.VK_DOWN:
                    model.down();
                    break;
            }
        }
        if (model.maxTile == WINNING_TILE)
            view.isGameWon = true;
        view.repaint();
    }

    public View getView() {
        return view;
    }
}
