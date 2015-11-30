package com.witmer.nicholas.ashman;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

/**
 * Created by nicho on 11/16/2015.
 */
public class Character
{
    private int curCol;
    private int curRow;
    private int color;
    private float size;
    private int direction;
    private String type;

    public Character(int col, int row, int color, String type)
    {
        this.curCol = col;
        this.curRow = row;
        this.color = color;
        this.size = .5f;
        this.type = type;
    }

    public Bundle bundleCharacter()
    {
        Bundle b = new Bundle();
        b.putInt("col", this.curCol);
        b.putInt("row", this.curRow);
        b.putInt("direction", this.direction);
        b.putInt("color", this.color);
        b.putString("type", this.type);
        return b;
    }

    public Character(Bundle b)
    {
        this.curCol = b.getInt("col");
        this.curRow = b.getInt("row");
        this.color = b.getInt("color");
        this.direction = b.getInt("direction");
        this.type = b.getString("type");
        this.size = .5f;
    }

    public int getCurCol()
    {
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean checkLocation(int i, int j)
    {
        if(this.curCol == i && this.curRow == j)
        {
            return true;
        }
        return false;
    }

    public void setPosition(int col, int row)
    {
        this.curCol = col;
        this.curRow = row;
    }

    public void drawCharacter(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(this.color);
        c.drawCircle(this.curRow+.5f, this.curCol+.5f, this.size, p);
    }
}
