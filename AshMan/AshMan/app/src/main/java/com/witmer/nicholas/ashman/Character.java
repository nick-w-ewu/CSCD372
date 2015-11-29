package com.witmer.nicholas.ashman;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by nicho on 11/16/2015.
 */
public class Character
{
    private int curCol;
    private int curRow;
    private float curColPosition;
    private float curRowPosition;
    private int color;
    private float size;
    private int direction;
    private String type;

    public Character(int col, int row, int color, String type)
    {
        this.curCol = col;
        this.curRow = row;
        this.curColPosition = col;
        this.curRowPosition = row;
        this.color = color;
        this.size = .5f;
        this.type = type;
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

    public float getCurColPosition()
    {
        return curColPosition;
    }

    public void setCurColPosition(float curColPosition)
    {
        this.curColPosition = curColPosition;
    }

    public float getCurRowPosition()
    {
        return curRowPosition;
    }

    public void setCurRowPosition(float curRowPosition)
    {
        this.curRowPosition = curRowPosition;
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

    public void moveOneStep()
    {
        switch (direction)
        {
            case 1:
                this.curRowPosition+=.25f;
            case 2:

                this.curRowPosition-=.25f;
            case 3:
                this.curColPosition+=.25f;
            case 4:
                this.curColPosition+=.25f;
        }
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
        this.curColPosition = col;
        this.curRowPosition = row;
    }


    public void drawCharacter(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(this.color);
        c.drawCircle(this.curRowPosition+.5f, this.curColPosition+.5f, this.size, p);
    }
}
