package com.witmer.nicholas.ashman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nicho on 11/12/2015.
 */
public class Maze extends View
{
    private int[][] maze = {{1,2,2,2,2,1,1,2,2,2,1,1,1,2},
                            {2,1,1,2,1,2,1,2,1,1,2,2,1,1},
                            {1,2,1,1,1,2,2,1,1,2,1,1,2,1},
                            {2,2,2,1,1,2,1,2,2,1,1,2,2,1},
                            {2,1,1,2,1,1,2,2,2,1,2,1,1,1},
                            {1,2,1,1,2,2,1,1,2,2,2,2,2,2},
                            {2,1,2,2,2,2,1,1,1,1,2,1,2,1},
                            {2,1,2,2,1,2,1,2,1,2,1,1,2,1},
                            {2,2,2,1,2,1,2,2,2,2,1,1,1,1},
                            {2,1,2,1,2,2,2,2,2,1,2,1,1,1},
                            {2,1,2,1,1,1,2,2,2,2,2,2,2,1},
                            {2,1,1,2,2,1,1,1,2,2,1,1,2,1},
                            {1,1,2,1,2,2,1,2,1,2,2,1,2,2},
                            {2,1,1,2,2,2,1,1,2,1,2,2,1,2}};

    private int blue = Color.rgb(51, 51, 255);
    public Maze(Context context)
    {
        super(context);
    }

    public Maze(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public Maze(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        c.scale(28,28);
        c.drawColor(blue);
        Path square = new Path();
        for(int i = 0; i < this.maze.length; i++)
        {
            for(int j = 0; j < this.maze[0].length; j++)
            {
                if(this.maze[i][j] == 1)
                {
                    square.moveTo(i, j);
                    square.lineTo(i, j + 1);
                    square.lineTo(i + 1, j + 1);
                    square.lineTo(i + 1, j);
                    square.close();
                    c.clipPath(square, Region.Op.REPLACE);
                    c.drawColor(Color.BLACK);
                }
            }
        }
    }
}
