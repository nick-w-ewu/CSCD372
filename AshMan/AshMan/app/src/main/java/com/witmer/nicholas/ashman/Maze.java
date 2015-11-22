package com.witmer.nicholas.ashman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nicho on 11/12/2015.
 */
public class Maze extends View
{
    private int[][] maze = {{0,2,2,2,2,2,1,2,2,2,2,1,1,1},
                            {2,2,1,2,1,2,1,2,1,2,2,2,2,1},
                            {1,2,1,1,1,2,2,2,1,2,1,1,2,1},
                            {2,2,2,2,1,2,1,2,2,1,2,2,2,1},
                            {2,2,1,2,2,2,2,2,2,1,2,1,1,1},
                            {1,2,1,1,2,2,1,1,2,2,2,2,2,2},
                            {2,1,2,2,2,2,1,1,1,1,2,1,2,1},
                            {2,1,2,2,1,2,1,2,1,2,1,1,2,1},
                            {2,2,2,1,2,2,2,2,2,2,1,1,2,1},
                            {2,1,2,1,2,2,2,1,1,1,2,1,2,1},
                            {2,1,2,2,1,1,2,2,2,2,2,2,2,1},
                            {2,1,1,2,2,1,1,1,2,2,1,1,2,1},
                            {2,2,2,1,2,2,1,2,2,2,2,1,2,2},
                            {1,1,1,2,2,2,1,1,2,1,2,2,1,2}};

    private int[][] maze1 = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,2,2,2,2,2,1,2,2,2,2,1,1,1,1},
            {1,2,2,1,2,1,2,1,2,1,2,2,2,2,1,1},
            {1,1,2,1,1,1,2,2,2,1,2,1,1,2,1,1},
            {1,2,2,2,2,1,2,1,2,2,1,2,2,2,1,1},
            {1,2,2,1,2,2,2,2,2,2,1,2,1,1,1,1},
            {1,1,2,1,1,2,2,1,1,2,2,2,2,2,2,1},
            {1,2,1,2,2,2,2,1,1,1,1,2,1,2,1,1},
            {1,2,1,2,2,1,2,1,2,1,2,1,1,2,1,1},
            {1,2,2,2,1,2,2,2,2,2,2,1,1,2,1,1},
            {1,2,1,2,1,2,2,2,1,1,1,2,1,2,1,1},
            {1,2,1,2,2,1,1,2,2,2,2,2,2,2,1,1},
            {1,2,1,1,2,2,1,1,1,2,2,1,1,2,1,1},
            {1,2,2,2,1,2,2,1,2,2,2,2,1,2,2,1},
            {1,1,1,1,2,2,2,1,1,2,1,2,2,1,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    private int blue = Color.rgb(51, 51, 255);
    private int cHight;
    private int cWidth;
    private float oHeigth = 14;
    private float oWidth = 14;
    private Character pacMan;

    public Maze(Context context)
    {
        super(context);
        createGameCharacter(0, 0, Color.rgb(255,255,0));
    }

    public Maze(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        createGameCharacter(0, 0, Color.rgb(255,255,0));
    }

    public Maze(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        createGameCharacter(0, 0, Color.rgb(255,255,0));
    }

    private void createGameCharacter(int col, int row, int color)
    {
        this.pacMan = new Character(col, row, color);
    }

    public void moveCharacter(int direction)
    {
        switch (direction)
        {
            case 1:
                moveUp();
                break;
            case 2:
                moveDown();
                break;
            case 3:
                moveRight();
                break;
            case 4:
                moveLeft();
                break;
        }
    }

    private void moveLeft()
    {
        int i = this.pacMan.getCurCol();
        int j = this.pacMan.getCurRow();

        if(j-1 >= 0 && this.maze[i][j-1] != 1)
        {
            this.pacMan.setCurRow(j-1);
            this.maze[i][j-1] = 0;
            invalidate();
        }
    }

    private void moveRight()
    {
        int i = this.pacMan.getCurCol();
        int j = this.pacMan.getCurRow();

        if(j+1 < this.maze[0].length && this.maze[i][j+1] != 1)
        {
            this.pacMan.setCurRow(j+1);
            this.maze[i][j+1] = 0;
            invalidate();
        }
    }

    private void moveDown()
    {
        int i = this.pacMan.getCurCol();
        int j = this.pacMan.getCurRow();

        while(i+1 < this.maze.length && this.maze[i+1][j] != 1)
        {
            this.pacMan.setCurCol(i+1);
            this.maze[i+1][j] = 0;
            invalidate();
            i++;
        }
    }

    private void moveUp()
    {
        int i = this.pacMan.getCurCol();
        int j = this.pacMan.getCurRow();
        if(i-1 >= 0 && this.maze[i-1][j] !=1)
        {
            this.pacMan.setCurCol(i-1);
            this.maze[i-1][j] = 0;
            invalidate();
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int odlw, int oldh)
    {
        super.onSizeChanged(w, h, odlw, oldh);
        this.cHight = h;
        this.cWidth = w;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = 0;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width > height) {
            size = height;
        } else {
            size = width;
        }
        setMeasuredDimension(size, size);
    }

    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        float wScale = cWidth/(float)this.oWidth;
        float hScale = cHight/(float)this.oHeigth;
        c.scale(wScale, hScale);
        c.drawColor(blue);
        Path square = new Path();
        Paint p = new Paint();
        p.setColor(this.pacMan.getColor());
        c.drawCircle(this.pacMan.getCurRow()+.5f, this.pacMan.getCurCol()+.5f, .5f, p);
        p.setColor(Color.WHITE);

        for(int i = 0; i < this.maze.length; i++)
        {
            for(int j = 0; j < this.maze[0].length; j++)
            {
                if(this.maze[i][j] == 2)
                {
                    c.drawCircle(j+.5f, i+0.5f, .25f, p);
                }
            }
        }
        for(int i = 0; i < this.maze.length; i++)
        {
            for(int j = 0; j < this.maze[0].length; j++)
            {
                if (this.maze[i][j] == 1)
                {
                    square.moveTo(j, i);
                    square.lineTo(j + 1, i);
                    square.lineTo(j + 1, i + 1);
                    square.lineTo( j, i + 1);
                    square.close();
                    c.clipPath(square, Region.Op.REPLACE);
                    c.drawColor(Color.BLACK);
                }
            }
        }
    }
}
