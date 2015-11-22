package com.witmer.nicholas.ashman;

/**
 * Created by nicho on 11/16/2015.
 */
public class Character
{
    private int curCol;
    private int curRow;
    private int color;

    public Character(int col, int row, int color)
    {
        this.curCol = col;
        this.curRow = row;
        this.color = color;
    }

    public int getCurCol() {
        return curCol;
    }

    public int getCurRow() {
        return curRow;
    }

    public int getColor() {
        return color;
    }

    public void setCurCol(int curCol) {
        this.curCol = curCol;
    }

    public void setCurRow(int curRow) {
        this.curRow = curRow;
    }
}
