package com.witmer.nicholas.npwitmerlab4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nicho on 10/22/2015.
 */
public class SevenSegment extends View
{
    private int curNum;
    private int[] curSegments;
    private final int[][] numTable= {{1,0,1,1,1,1,1},
                                     {0,0,0,0,0,1,1},
                                     {1,1,1,0,1,0,1},
                                     {1,1,1,0,0,1,1},
                                     {0,1,0,1,0,1,1},
                                     {1,1,1,1,0,1,0},
                                     {1,1,1,1,1,1,0},
                                     {1,0,0,0,0,1,1},
                                     {1,1,1,1,1,1,1},
                                     {1,1,1,1,0,1,1},
                                     {0,0,0,0,0,0,0}};
    private final float oHight = 32f;
    private final float oWidth = 20f;
    private float cHight;
    private float cWidth;
    private final int margin = 2;
    private final float aspectRatio = 1f;//Yes I know this is weird, but it was the only way to get the display working correctly
    private final float[] verticies = {4,4, 2,6, 2,14, 4,16, 6,14, 6,6};
    private final int on = Color.rgb(255, 0, 0);
    private final int off = Color.rgb(76,0,0);

    public SevenSegment(Context context)
    {
        super(context);
        initializeValues();
    }

    public SevenSegment(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initializeValues();
    }

    public SevenSegment(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initializeValues();
    }

    @Override
    public Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("currentNum", this.curNum);

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state)
    {
        if(state instanceof Bundle)
        {
            Bundle b = (Bundle)state;
            this.curNum = b.getInt("currentNum");
            this.curSegments = this.numTable[this.curNum];
            state = b.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(state);
    }

    private void initializeValues()
    {
        this.curNum = 10;
        this.curSegments = numTable[10];
    }

    public int getCurNum()
    {
        return this.curNum;
    }

    public void setCurNum(int num)
    {
        if(num > 9)
        {
            this.curNum = 10;
            this.curSegments = this.numTable[10];
        }
        else
        {
            this.curNum = num;
            this.curSegments = numTable[num];
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int odlw, int oldh)
    {
        super.onSizeChanged(w, h, odlw, oldh);
        this.cHight = h;
        this.cWidth = w;
    }

    @Override
    protected void onMeasure(int mWidth, int mHeight)
    {
        super.onMeasure(mWidth, mHeight);
        int fWidth, fHeight;
        int width, height;
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        fWidth = (int)(height * this.aspectRatio);
        fHeight = (int)(width/this.aspectRatio);

        if(fHeight > height)
        {
            setMeasuredDimension(height, fWidth);
        }
        else
        {
            setMeasuredDimension(fHeight, width);
        }
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Path segment ;
        setLayerType(this.LAYER_TYPE_SOFTWARE, null);
        float wScale = cWidth/(float)this.oWidth;
        float hScale = cHight/(float)this.oHight;
        canvas.drawColor(Color.BLACK);
        canvas.scale(wScale, hScale);
        canvas.save();
        drawPathH(canvas, 0);
        canvas.translate(0, 12);
        drawPathH(canvas, 1);
        canvas.translate(0,12);
        drawPathH(canvas, 2);
        canvas.restore();
        drawPathV(canvas, 3);
        canvas.translate(0,12);
        drawPathV(canvas, 4);
        canvas.translate(12, 0);
        drawPathV(canvas, 5);
        canvas.translate(0, -12);
        drawPathV(canvas, 6);
    }

    private void drawPathV(Canvas canvas, int line)
    {
        Path segment = new Path();
        segment.moveTo(verticies[0], verticies[1]);
        for(int i = 2; i < verticies.length-1; i= i + 2)
        {
            segment.lineTo(verticies[i], verticies[i+1]);
        }
        segment.close();
        canvas.clipPath(segment, Region.Op.REPLACE);
        if(this.curSegments[line] == 1)
        {
            canvas.drawColor(on);
        }
        else
        {
            canvas.drawColor(off);
        }
    }

    private void drawPathH(Canvas canvas, int line)
    {
        Path segment = new Path();
        segment.moveTo(verticies[0], verticies[1]);
        for(int i = 2; i < verticies.length-1; i= i + 2)
        {
            segment.lineTo(verticies[i+1], verticies[i]);
        }
        segment.close();
        canvas.clipPath(segment, Region.Op.REPLACE);
        if(this.curSegments[line] == 1)
        {
            canvas.drawColor(on);
        }
        else
        {
            canvas.drawColor(off);
        }
    }
}
