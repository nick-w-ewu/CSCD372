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
    private int[][] maze = {{1,2,2,2,2,2,1,2,2,2,2,1,1,1},
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

    private int blue = Color.rgb(51, 51, 255);
    private int cHight;
    private int cWidth;
    private float oHeigth = 14;
    private float oWidth = 14;

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
        for(int i = 0; i < this.maze.length; i++)
        {
            for(int j = 0; j < this.maze[0].length; j++)
            {
                if(this.maze[i][j] == 2)
                {
                    Paint p = new Paint();
                    p.setColor(Color.WHITE);
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
