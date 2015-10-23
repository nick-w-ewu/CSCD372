package com.witmer.nicholas.npwitmerlab4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nicho on 10/22/2015.
 */
public class SevenSegment extends View
{
    private int curNum;
    private int[] curSegments;
    private final int hight = 32;
    private final int width = 20;
    private final int margin = 2;
    private final float aspectRatio = width/hight;
    private final float[] vertices = {6, 6, 12, 6, 14, 4, 12, 2, 6, 2, 4, 4};
    private final int on = Color.rgb(255, 0, 0);

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

    private void initializeValues()
    {
        this.curNum = 0;
        this.curSegments = new int[7];
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        setLayerType(this.LAYER_TYPE_SOFTWARE, null);
        float canvasWidth = getWidth();
        float canvasHight = getHeight();
        float wScale = canvasWidth/(float)this.width;
        float hScale = canvasHight/(float)this.hight;
        canvas.scale(wScale, hScale);
        canvas.save();
        makePath(canvas);
        canvas.translate(0,12);
        makePath(canvas);
    }

    private void makePath(Canvas canvas)
    {
        Path segment = new Path();
        segment.moveTo(this.vertices[0], this.vertices[1]);
        segment.lineTo(this.vertices[2], this.vertices[3]);
        segment.lineTo(this.vertices[4], this.vertices[5]);
        segment.lineTo(this.vertices[6], this.vertices[7]);
        segment.lineTo(this.vertices[8], this.vertices[9]);
        segment.lineTo(this.vertices[10], this.vertices[11]);
        segment.close();
        canvas.clipPath(segment);
        canvas.drawColor(on);
    }
}
