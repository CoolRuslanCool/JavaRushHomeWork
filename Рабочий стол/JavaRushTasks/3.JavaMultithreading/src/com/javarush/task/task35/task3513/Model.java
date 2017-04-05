package com.javarush.task.task35.task3513;

import java.util.*;

/**
 * Created by ruslan on 26.03.17.
 */
public class Model {
    protected int score;
    protected int maxTile;
    private static final int FIELD_WIDTH = 4;
    private boolean isSaveNeeded = true;
    private Tile[][] gameTiles;
    private Stack<Tile[][]> previousStates;
    private Stack<Integer> previousScores;

    public Model() { resetGameTiles(); }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) return true;
        for (int y = 0;y<FIELD_WIDTH;y++) {
            for (int x = 0;x<FIELD_WIDTH-1;x++) {
                if (gameTiles[y][x].value == gameTiles[y][x+1].value)
                    return true;
            }
        }
        for (int x = 0;x<FIELD_WIDTH;x++) {
            for (int y = 0;y<FIELD_WIDTH-1;y++) {
                if (gameTiles[y][x].value == gameTiles[y+1][x].value)
                    return true;
            }
        }
        return false;
    }

    public boolean hasBoardChanged() {
        int weightGameTiles = 0, weightStackPeek = 0;
        for (Tile[] mas: gameTiles)
            for (Tile tile: mas)
                weightGameTiles += tile.value;
        Tile[][] stackMas = previousStates.peek();
        for (Tile[] mas: stackMas)
            for (Tile tile: mas)
                weightStackPeek += tile.value;
        return weightGameTiles != weightStackPeek;
    }
    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged())
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, score, move);
        rollback();
        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this::left));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));
        queue.peek().getMove().move();
    }

    public void randomMove() {
        switch ((int)(Math.random()*100)%4) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    private void saveState(Tile[][] state) {
        Tile[][] copy = new Tile[state.length][state[0].length];
        for (int y = 0; y < state.length; y++)
            for (int x = 0; x < state[0].length; x++)
                copy[y][x] = new Tile(state[y][x].value);
        previousStates.push(copy);
        previousScores.push(Integer.valueOf(score));
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean wasChange = false;
        for (Tile[] mas : gameTiles)
            if (compressTiles(mas) | mergeTiles(mas)) {
                    wasChange = true;
            }
        if (wasChange)
            addTile();
        isSaveNeeded = true;
    }

    public void up() {
        saveState(gameTiles);
        rotateTiles();
        left();
        rotateTiles();
        rotateTiles();
        rotateTiles();
    }

    public void right() {
        saveState(gameTiles);
        rotateTiles();
        rotateTiles();
        left();
        rotateTiles();
        rotateTiles();
    }

    public void down() {
        saveState(gameTiles);
        rotateTiles();
        rotateTiles();
        rotateTiles();
        left();
        rotateTiles();
    }


    private void rotateTiles() {
        Tile[][] copy = gameTiles;
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int x = 0;x<FIELD_WIDTH;x++)
            for (int y = 0; y<FIELD_WIDTH;y++)
                gameTiles[y][x] = copy[x][FIELD_WIDTH-1-y];
    }

    protected void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (Tile[] tiles: gameTiles)
            Arrays.fill(tiles, new Tile());
        score = 0;
        maxTile = 2;
        previousScores = new Stack();
        previousStates = new Stack();
        addTile();
        addTile();
        for (Tile[] tileMas: gameTiles)
            for (Tile tile: tileMas)
                if (tile.value>maxTile)
                    maxTile = tile.value;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
            for (int j = 1; j < tiles.length; j++) {
                if (tiles[j - 1].isEmpty() && !tiles[j].isEmpty()) {
                    change = true;
                    tiles[j - 1] = tiles[j];
                    tiles[j] = new Tile();
                }
            }
        }
        return change;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
            if ((tiles[i - 1].value == tiles[i].value) && !tiles[i - 1].isEmpty() && !tiles[i].isEmpty()) {
                change = true;
                tiles[i - 1].value *= 2;
                score += tiles[i - 1].value;
                maxTile = maxTile > tiles[i - 1].value ? maxTile : tiles[i - 1].value;
                tiles[i] = new Tile();
                compressTiles(tiles);
            }
        }
        return change;
    }

    private void addTile() {
        List<Tile> tiles = getEmptyTiles();
        if (!tiles.isEmpty())
            tiles.get((int)(Math.random()*tiles.size())).value = (Math.random()<0.9) ? 2 : 4;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> result = new LinkedList<>();
        for (Tile[] array: gameTiles)
            for (Tile tile: array)
                if (tile.value == 0)
                    result.add(tile);
        return result;
    }
}
