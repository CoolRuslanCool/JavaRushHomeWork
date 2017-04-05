package com.javarush.task.task35.task3513;

/**
 * Created by ruslan on 30.03.17.
 */
public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() { return move; }

    @Override
    public int compareTo(MoveEfficiency moveEfficiency) {
        return (numberOfEmptyTiles != moveEfficiency.numberOfEmptyTiles) ? Integer.compare(numberOfEmptyTiles, moveEfficiency.numberOfEmptyTiles) : Integer.compare(this.score, moveEfficiency.score);
    }
}
