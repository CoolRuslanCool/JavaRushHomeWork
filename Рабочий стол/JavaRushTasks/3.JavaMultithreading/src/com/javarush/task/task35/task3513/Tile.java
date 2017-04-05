package com.javarush.task.task35.task3513;

import java.awt.*;

/**
 * Created by ruslan on 26.03.17.
 */
public class Tile {
    int value;

    public Tile() {
        value = 0;
    }
    public Tile(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return (value == 0) ? true : false;
    }

    public Color getFontColor() {
        return (value < 16) ? new Color(0x776e65) : new Color(0xf9f6f2);
    }

    public Color getTileColor() {
        int res;
        switch (value) {
            case 0: res = 0xcdc1b4; break;
            case 2: res = 0xeee4da; break;
            case 4: res = 0xede0c8; break;
            case 8: res = 0xf2b179; break;
            case 16: res = 0xf59563; break;
            case 32: res = 0xf67c5f; break;
            case 64: res = 0xf65e3b; break;
            case 128: res = 0xedcf72; break;
            case 256: res = 0xedcc61; break;
            case 512: res = 0xedc850; break;
            case 1024: res = 0xedc53f; break;
            case 2048: res = 0xedc22e; break;
            default: res = 0xff0000;
        }
        return new Color(res);
    }
}
