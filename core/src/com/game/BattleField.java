package com.game;

public class BattleField {

    public int[][] field;

    public BattleField(int fieldSize) {
        this.field = new int[fieldSize][fieldSize];
    }

    public void placeMe (int xOld, int yOld, int xNew, int yNew) {
        field[xOld][yOld] = 0;
        field[xNew][yNew] = 1;
    }

    public void placeMe (int xNew, int yNew) {
        field[xNew][yNew] = 1;
    }

    public boolean isValidPos (int xNew, int yNew) {
        if((xNew < 0 || xNew >= field.length || yNew < 0 || yNew >= field.length)) return false;
        else return field[xNew][yNew] == 0;
    }
}
